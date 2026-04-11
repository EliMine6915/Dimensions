package de.elimine6915.dimensions.listener;

import de.elimine6915.dimensions.Dimensions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockMinedListener implements Listener {

    private final Dimensions plugin;

    public BlockMinedListener(Dimensions plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
    }
}
