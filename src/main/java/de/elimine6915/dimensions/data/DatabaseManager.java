package de.elimine6915.dimensions.data;

import java.sql.*;
import java.util.UUID;
import java.io.File;

public class DatabaseManager {
    private Connection connection;

    public void connect(File dataFolder) throws SQLException {
        if (!dataFolder.exists()) dataFolder.mkdirs();
        connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder + "/playerdata.db");

        try (Statement s = connection.createStatement()) {
            s.execute("CREATE TABLE IF NOT EXISTS stats (" +
                    "uuid TEXT PRIMARY KEY, " +
                    "mining INTEGER DEFAULT 0, miningXP INTEGER DEFAULT 0, " +
                    "foraging INTEGER DEFAULT 0, foragingXP INTEGER DEFAULT 0, " +
                    "farming INTEGER DEFAULT 0, farmingXP INTEGER DEFAULT 0, " +
                    "digging INTEGER DEFAULT 0, diggingXP INTEGER DEFAULT 0, " +
                    "fishing INTEGER DEFAULT 0, fishingXP INTEGER DEFAULT 0, " +
                    "combat INTEGER DEFAULT 0, combatXP INTEGER DEFAULT 0, " +
                    "seasonLVL INTEGER DEFAULT 0, seasonXP INTEGER DEFAULT 0)");
        }
    }

    public PlayerProfile loadProfile(UUID uuid) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM stats WHERE uuid = ?")) {
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new PlayerProfile(uuid,
                        rs.getInt("mining"), rs.getInt("miningXP"),
                        rs.getInt("foraging"), rs.getInt("foragingXP"),
                        rs.getInt("farming"), rs.getInt("farmingXP"),
                        rs.getInt("digging"), rs.getInt("diggingXP"),
                        rs.getInt("fishing"), rs.getInt("fishingXP"),
                        rs.getInt("combat"), rs.getInt("combatXP"),
                        rs.getInt("seasonLVL"), rs.getInt("seasonXP")
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        // Fallback: Alles auf 0, falls der Spieler neu ist
        return new PlayerProfile(uuid, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public void saveProfile(PlayerProfile p) {
        String query = "INSERT INTO stats (uuid, mining, miningXP, foraging, foragingXP, farming, farmingXP, " +
                "digging, diggingXP, fishing, fishingXP, combat, combatXP, seasonLVL, seasonXP) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " +
                "ON CONFLICT(uuid) DO UPDATE SET " +
                "mining=excluded.mining, miningXP=excluded.miningXP, foraging=excluded.foraging, " +
                "foragingXP=excluded.foragingXP, farming=excluded.farming, farmingXP=excluded.farmingXP, " +
                "digging=excluded.digging, diggingXP=excluded.diggingXP, fishing=excluded.fishing, " +
                "fishingXP=excluded.fishingXP, combat=excluded.combat, combatXP=excluded.combatXP, " +
                "seasonLVL=excluded.seasonLVL, seasonXP=excluded.seasonXP";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, p.getUuid().toString());
            ps.setInt(2, p.getMining()); ps.setInt(3, p.getMiningXP());
            ps.setInt(4, p.getForaging()); ps.setInt(5, p.getForagingXP());
            ps.setInt(6, p.getFarming()); ps.setInt(7, p.getFarmingXP());
            ps.setInt(8, p.getDigging()); ps.setInt(9, p.getDiggingXP());
            ps.setInt(10, p.getFishing()); ps.setInt(11, p.getFishingXP());
            ps.setInt(12, p.getCombat()); ps.setInt(13, p.getCombatXP());
            ps.setInt(14, p.getSeasonLVL()); ps.setInt(15, p.getSeasonXP());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}