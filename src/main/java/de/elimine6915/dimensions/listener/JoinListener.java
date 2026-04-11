package de.elimine6915.dimensions.listener;

import de.elimine6915.dimensions.Dimensions;
import de.elimine6915.dimensions.data.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinListener implements Listener {

    private final Dimensions plugin;

    public JoinListener(Dimensions plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();

        e.setJoinMessage(ChatColor.GOLD + "[+] " + ChatColor.WHITE + p.getName() + ChatColor.GREEN + " ist gejoint!");

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            // Daten aus SQL laden
            PlayerProfile profile = plugin.getDatabaseManager().loadProfile(uuid);
            plugin.getCache().put(uuid, profile);

            // Zurück in den Main-Thread springen, um das Scoreboard zu setzen (Sicherer)
 //           Bukkit.getScheduler().runTask(plugin, () -> {
 //               HubScoreboard scoreboard = new HubScoreboard(this.plugin);
 //               scoreboard.updateScoreboard(p);
 //           });
        });
    }
}