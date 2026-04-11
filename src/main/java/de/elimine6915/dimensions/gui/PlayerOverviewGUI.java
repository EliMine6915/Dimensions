package de.elimine6915.dimensions.gui;

import de.elimine6915.dimensions.Dimensions;
import de.elimine6915.dimensions.data.PlayerProfile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MenuType;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class PlayerOverviewGUI {
    Dimensions plugin = Dimensions.getInstance();

    public PlayerOverviewGUI(Dimensions plugin) {}

    public void openCustomGui(Player p) {
        InventoryView view = MenuType.GENERIC_9X3.builder()
                .title(Component.text("Dein Überblick", NamedTextColor.GOLD))
                .build(p);

        Inventory gui = view.getTopInventory();
        PlayerProfile profile = plugin.getCache().get(p.getUniqueId());

        int mining = 0, foraging = 0, farming = 0, digging = 0, fishing = 0, combat = 0, season = 0;
        int miningXP = 0, foragingXP = 0, farmingXP = 0, diggingXP = 0, fishingXP = 0, combatXP = 0, seasonXP = 0;

        if (profile != null) {
            mining = profile.getMining();
            foraging = profile.getForaging();
            farming = profile.getFarming();
            digging = profile.getDigging();
            fishing = profile.getFishing();
            combat = profile.getCombat();
            season = profile.getSeasonLVL();

            miningXP = profile.getMining();
            foragingXP = profile.getForaging();
            farmingXP = profile.getFarming();
            diggingXP = profile.getDigging();
            fishingXP = profile.getFishing();
            combatXP = profile.getCombat();
            seasonXP = profile.getSeasonXP();
        }

        //Player Head/Info
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta metaHead = (SkullMeta) playerHead.getItemMeta();
        if (metaHead != null) {
            metaHead.setOwningPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));
            String name = Bukkit.getOfflinePlayer(p.getUniqueId()).getName();
            metaHead.displayName(Component.text(name != null ? name : "Unbekannt", NamedTextColor.YELLOW)
                    .decoration(TextDecoration.ITALIC, false));
            playerHead.setItemMeta(metaHead);
        }
        gui.setItem(4, playerHead);

        //Mining
        ItemStack miningItem = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta metaMining = miningItem.getItemMeta();

        metaMining.displayName(Component.text("Mining", NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
                .decoration(TextDecoration.BOLD, true)
        );
        metaMining.lore(List.of(
                Component.text("§8Level: §f" + String.valueOf(mining))
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false),
                Component.text("§8XP: §f" + String.valueOf(miningXP))
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false)
        ));
        metaMining.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ATTRIBUTES);
        miningItem.setItemMeta(metaMining);
        gui.setItem(10, miningItem);

        //Foraging
        ItemStack foragingItem = new ItemStack(Material.STONE_AXE);
        ItemMeta metaForaging = foragingItem.getItemMeta();

        metaForaging.displayName(Component.text("Foraging", NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
                .decoration(TextDecoration.BOLD, true)
        );
        metaForaging.lore(List.of(
                Component.text("§8Level: §f" + String.valueOf(foraging), NamedTextColor.WHITE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false),
                Component.text("§8XP: §f" + String.valueOf(foragingXP), NamedTextColor.WHITE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false)
        ));
        metaForaging.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ATTRIBUTES);
        foragingItem.setItemMeta(metaForaging);
        gui.setItem(11, foragingItem);

        //Farming
        ItemStack farmingItem = new ItemStack(Material.STONE_HOE);
        ItemMeta metaFarming = farmingItem.getItemMeta();

        metaFarming.displayName(Component.text("Farming", NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
                .decoration(TextDecoration.BOLD, true)
        );
        metaFarming.lore(List.of(
                Component.text("§8Level: §f" + String.valueOf(farming), NamedTextColor.WHITE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false),
                Component.text("§8XP: §f" + String.valueOf(farmingXP), NamedTextColor.WHITE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false)
        ));
        metaFarming.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ATTRIBUTES);
        farmingItem.setItemMeta(metaFarming);
        gui.setItem(12, farmingItem);

        //Season Item
        ItemStack seasonItem = new ItemStack(Material.MELON);
        ItemMeta metaSeason = seasonItem.getItemMeta();

        metaSeason.displayName(Component.text("Melone", NamedTextColor.GOLD)
                .decoration(TextDecoration.ITALIC, false)
                .decoration(TextDecoration.BOLD, true)
        );
        metaSeason.lore(List.of(
                Component.text("§8Level: §f" + String.valueOf(season), NamedTextColor.WHITE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false),
                Component.text("§8XP: §f" + String.valueOf(seasonXP), NamedTextColor.WHITE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false)
        ));
        metaSeason.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ATTRIBUTES);
        seasonItem.setItemMeta(metaSeason);
        gui.setItem(13, seasonItem);

        //Digging
        ItemStack diggingItem = new ItemStack(Material.STONE_SHOVEL);
        ItemMeta metaDigging = diggingItem.getItemMeta();

        metaDigging.displayName(Component.text("Digging", NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
                .decoration(TextDecoration.BOLD, true)
        );
        metaDigging.lore(List.of(
                Component.text("§8Level: §f" + String.valueOf(digging), NamedTextColor.WHITE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false),
                Component.text("§8XP: §f" + String.valueOf(diggingXP), NamedTextColor.WHITE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false)
        ));
        metaDigging.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ATTRIBUTES);
        diggingItem.setItemMeta(metaDigging);
        gui.setItem(14, diggingItem);

        //Fishing
        ItemStack fishingItem = new ItemStack(Material.FISHING_ROD);
        ItemMeta metaFishing = fishingItem.getItemMeta();

        metaFishing.displayName(Component.text("Fishing", NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
                .decoration(TextDecoration.BOLD, true)
        );
        metaFishing.lore(List.of(
                Component.text("§8Level: §f" + String.valueOf(fishing), NamedTextColor.WHITE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false),
                Component.text("§8XP: §f" + String.valueOf(fishingXP), NamedTextColor.WHITE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false)
        ));
        metaFishing.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ATTRIBUTES);
        fishingItem.setItemMeta(metaFishing);
        gui.setItem(15, fishingItem);

        //Combat
        ItemStack combatItem = new ItemStack(Material.STONE_SWORD);
        ItemMeta metaCombat = combatItem.getItemMeta();

        metaCombat.displayName(Component.text("Combat", NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
                .decoration(TextDecoration.BOLD, true)
        );
        metaCombat.lore(List.of(
                Component.text("§8Level: §f" + String.valueOf(combat), NamedTextColor.WHITE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false),
                Component.text("§8XP: §f" + String.valueOf(combatXP), NamedTextColor.WHITE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, false)
        ));
        metaCombat.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ATTRIBUTES);
        combatItem.setItemMeta(metaCombat);
        gui.setItem(16, combatItem);

        view.open();
    }
}
