package de.elimine6915.dimensions.scoreboard;

import de.elimine6915.dimensions.Dimensions;
import de.elimine6915.dimensions.data.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class HubScoreboard {

    private final Dimensions plugin;

    public HubScoreboard(Dimensions plugin) {
        this.plugin = plugin;
    }


    public void updateScoreboard(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        // Kleiner Tipp: Wenn das Icon \uE001 nicht geladen ist,
        // siehst du im Scoreboard-Titel gar nichts. Teste sonst mal mit "TEST".
        Objective obj = board.registerNewObjective("hub", "dummy", "");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        // Profil sicher aus dem Cache holen
        PlayerProfile profile = plugin.getCache().get(player.getUniqueId());

        // Wir definieren Standardwerte, falls das Profil noch lädt
        int mining = 0, foraging = 0, farming = 0, digging = 0, fishing = 0, combat = 0;

        if (profile != null) {
            mining = profile.getMining();
            foraging = profile.getForaging();
            farming = profile.getFarming();
            digging = profile.getDigging();
            fishing = profile.getFishing();
            combat = profile.getCombat();
        }

        int targetWidth = 85;

        // LEERZEILE
        obj.getScore("§1").setScore(11);

        // ÜBERSCHRIFT
        obj.getScore("§6§lHub").setScore(10);

        obj.getScore("§7§l" + player.getName()).setScore(9);
        obj.getScore("§2").setScore(8);

        // SKILLS
        obj.getScore(formatScoreLine("Mining", String.valueOf(mining), targetWidth)).setScore(7);
        obj.getScore(formatScoreLine("Foraging", String.valueOf(foraging), targetWidth)).setScore(6);
        obj.getScore(formatScoreLine("Farming", String.valueOf(farming), targetWidth)).setScore(5);
        obj.getScore(formatScoreLine("Digging", String.valueOf(digging), targetWidth)).setScore(4);
        obj.getScore(formatScoreLine("Fishing", String.valueOf(fishing), targetWidth)).setScore(3);
        obj.getScore(formatScoreLine("Combat", String.valueOf(combat), targetWidth)).setScore(2);

        // FOOTER
        obj.getScore("§4").setScore(1);
        obj.getScore("§d§ldimensions.de").setScore(0);

        player.setScoreboard(board);
    }

    private String formatScoreLine(String label, String value, int targetPixelWidth) {
        String prefix = "§7" + label + ": ";
        String suffix = " §f" + value;

        int currentWidth = getPixelWidth(label + ": ") + getPixelWidth(value);

        int spacePixelWidth = 4;
        int neededSpaces = (targetPixelWidth - currentWidth) / spacePixelWidth;

        if (neededSpaces < 1) neededSpaces = 1;

        return prefix + " ".repeat(neededSpaces) + suffix;
    }

    private int getPixelWidth(String text) {
        int width = 0;
        for (char c : text.toCharArray()) {
            switch (c) {
                case 'i': case 'l': case 't': case ' ': case '.': case ':': width += 2; break;
                case 'f': case 'k': case 'I': width += 5; break;
                default: width += 6; break;
            }
        }
        return width;
    }
}
