package de.elimine6915.dimensions;

import de.elimine6915.dimensions.commands.OpenPlayerOverview;
import de.elimine6915.dimensions.commands.SelectDimensionCommand;
import de.elimine6915.dimensions.data.DatabaseManager;
import de.elimine6915.dimensions.data.PlayerProfile;
import de.elimine6915.dimensions.listener.JoinListener;
import de.elimine6915.dimensions.listener.QuitListener;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap; // WICHTIG: Import für HashMap
import java.util.Map;
import java.util.UUID;

public final class Dimensions extends JavaPlugin {

    // 1. Deklaration der Variablen auf Klassenebene
    private DatabaseManager databaseManager;
    private final Map<UUID, PlayerProfile> cache = new HashMap<>();

    // Getter-Methoden
    public DatabaseManager getDatabaseManager() { return databaseManager; }
    public Map<UUID, PlayerProfile> getCache() { return cache; }
    private static Dimensions instance;

    @Override
    public void onEnable() {
        instance = this;
        databaseManager = new DatabaseManager();
        try {
            databaseManager.connect(getDataFolder());
        } catch (SQLException e) {
            getLogger().severe("Datenbank konnte nicht geladen werden!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        //Listener
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new QuitListener(this), this);

        //Commands
        final SelectDimensionCommand selectDimensionCommand = new SelectDimensionCommand();
        final OpenPlayerOverview openPlayerOverview = new OpenPlayerOverview();
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register("select", selectDimensionCommand);
            commands.registrar().register("overview", openPlayerOverview);
        });

        getLogger().info("Dimensions Plugin gestartet.");
    }

    @Override
    public void onDisable() {
        for (PlayerProfile profile : cache.values()) {
            databaseManager.saveProfile(profile);
        }
    }

    public static Dimensions getInstance() {
        return instance;
    }
}