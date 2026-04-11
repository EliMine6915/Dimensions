package de.elimine6915.dimensions.commands;

import de.elimine6915.dimensions.gui.SelectDimensionGUI;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SelectDimensionCommand implements BasicCommand {

    @Override
    public void execute(@NotNull CommandSourceStack stack, @NotNull String[] args) {
        if (!(stack.getExecutor() instanceof Player player)) {
            stack.getSender().sendMessage("Nur Spieler können diesen Befehl nutzen!");
            return;
        }

        SelectDimensionGUI gui = new SelectDimensionGUI();
        gui.openCustomGui(player);
    }
}