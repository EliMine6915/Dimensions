package de.elimine6915.dimensions.commands;

import de.elimine6915.dimensions.Dimensions;
import de.elimine6915.dimensions.gui.PlayerOverviewGUI;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OpenPlayerOverview implements BasicCommand {
    Dimensions plugin = Dimensions.getInstance();

    @Override
    public void execute(@NotNull CommandSourceStack stack, @NotNull String[] args) {
        if (!(stack.getExecutor() instanceof Player p)) {
            stack.getSender().sendMessage("Nur Spieler können diesen Befehl nutzen!");
            return;
        }

        PlayerOverviewGUI gui = new PlayerOverviewGUI(plugin);
        gui.openCustomGui(p);
    }
}
