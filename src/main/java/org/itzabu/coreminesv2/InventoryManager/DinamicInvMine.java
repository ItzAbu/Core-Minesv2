package org.itzabu.coreminesv2.InventoryManager;

import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.itzabu.coreminesv2.GUIS.MainGui.Items.MineResetButton;
import org.itzabu.coreminesv2.Mines.Inizializer;

import java.io.File;
import java.util.*;

public class DinamicInvMine {

    static Map<UUID, String> using = new HashMap<>();

    public DinamicInvMine() {
        return;
    }

    static public String getDinInv(String s) {

        File file = new File("plugins/CoreMinesV2/Mines/" + s + ".yml");
        Inventory empty = Bukkit.createInventory(null, 9*6);
        if(!file.exists()) {

            return "Mine non trovata";
        }
        FileConfiguration config = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(file);
        if(using.containsValue(s)) {
            return "Mine giá in modifica";
        }


        return "";
    }

    static public Inventory openDinInv(String s, UUID p) {
        using.put(p, s);
        Inventory inv = org.bukkit.Bukkit.createInventory(new CustomInvHolder(p.toString()), 9*6, s);
        ItemStack mine = Inizializer.GetMine(s);
        inv.setItem(13, mine);
        ItemStack reset = new MineResetButton().getMineResetButton();
        inv.setItem(28, reset);
        return inv;
    }



    static public void closeDinInv(Player p) {
        using.remove(p.getUniqueId());
    }
}
