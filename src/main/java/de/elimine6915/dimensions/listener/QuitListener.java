package de.elimine6915.dimensions.listener;

import de.elimine6915.dimensions.Dimensions;
import de.elimine6915.dimensions.data.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private final Dimensions plugin;

    public QuitListener(Dimensions plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        PlayerProfile profile = plugin.getCache().remove(p.getUniqueId());

        e.setQuitMessage(ChatColor.GOLD + "[-] " + ChatColor.WHITE + p.getName() + ChatColor.RED + " ist geleaved!");

        if (profile != null) {
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                plugin.getDatabaseManager().saveProfile(profile);
            });
        }
    }
}
