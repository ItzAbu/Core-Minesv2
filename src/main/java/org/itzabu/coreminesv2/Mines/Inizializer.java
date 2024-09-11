package org.itzabu.coreminesv2.Mines;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.itzabu.coreminesv2.Core_Minesv2;
import org.itzabu.coreminesv2.CustomConfigs.customConfig;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Inizializer {
    static File allMines = customConfig.getAllMine();
    static List<ItemStack> mines = new ArrayList<>();
    public static List<ItemStack> GetMines() {
        if(allMines.list() == null) {
            System.out.println("No mines found");
            return null;
        }
        for(String mine : allMines.list()) {
            File mineFile = new File("plugins/CoreMinesV2/Mines/" + mine);
            FileConfiguration mineConfig = YamlConfiguration.loadConfiguration(mineFile);
            ItemStack mineItem = new ItemStack(Material.IRON_BARS);
            ItemMeta mineMeta = mineItem.getItemMeta();
            mineMeta.setDisplayName(mineConfig.getString("Name"));
            NamespacedKey key = new NamespacedKey(Core_Minesv2.getInstance(), mine);
            mineMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, mine);
            mineItem.setItemMeta(mineMeta);
            mines.add(mineItem);
        }
        return mines;
    }
    public static ItemStack GetMine(String s) {
        File mineFile = new File("plugins/CoreMinesV2/Mines/" + s);
        FileConfiguration mineConfig = YamlConfiguration.loadConfiguration(mineFile);
        ItemStack mineItem = new ItemStack(Material.IRON_BARS);
        ItemMeta mineMeta = mineItem.getItemMeta();
        mineMeta.setDisplayName(s);
        NamespacedKey key = new NamespacedKey(Core_Minesv2.getInstance(), s);
        mineMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, s);
        mineItem.setItemMeta(mineMeta);
        return mineItem;
    }


}
