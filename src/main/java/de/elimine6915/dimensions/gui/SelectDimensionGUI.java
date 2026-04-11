package de.elimine6915.dimensions.gui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MenuType;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SelectDimensionGUI {
    public void openCustomGui(Player p) {
        // 1. Erstelle das View-Objekt über den Builder
        InventoryView view = MenuType.GENERIC_9X3.builder()
                .title(Component.text("Wähle eine Dimension", NamedTextColor.GOLD))
                .build(p);

        // 2. Items im oberen Inventar platzieren
        Inventory gui = view.getTopInventory();

        //Skyblock
        ItemStack skyblock = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta metaSky = skyblock.getItemMeta();

        metaSky.displayName(Component.text("Skyblock", NamedTextColor.BLUE)
                .decoration(TextDecoration.ITALIC, false)
                .decoration(TextDecoration.BOLD, true)
        );
        metaSky.lore(List.of(
                Component.text("Klicke hier, um die Skyblock", NamedTextColor.WHITE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false),
                Component.text("Dimensions als deine Heimat zu wählen.", NamedTextColor.WHITE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false)
        ));
        skyblock.setItemMeta(metaSky);
        gui.setItem(12, skyblock);

        //Stoneblock
        ItemStack stoneblock = new ItemStack(Material.STONE);
        ItemMeta metaStone = stoneblock.getItemMeta();

        metaStone.displayName(Component.text("Stoneblock", NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
                .decoration(TextDecoration.BOLD, true)
        );
        metaStone.lore(List.of(
                Component.text("Klicke hier, um die Stoneblock", NamedTextColor.WHITE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false),
                Component.text("Dimensions als deine Heimat zu wählen.", NamedTextColor.WHITE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false)
        ));
        stoneblock.setItemMeta(metaStone);
        gui.setItem(14, stoneblock);

        view.open();
    }
}
