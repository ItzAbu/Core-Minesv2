package org.itzabu.coreminesv2.InventoryManager;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.itzabu.coreminesv2.Core_Minesv2;
import org.itzabu.coreminesv2.GUIS.MainGui.Items.DelMine;
import org.itzabu.coreminesv2.GUIS.MainGui.Items.NewMine;
import org.itzabu.coreminesv2.Mines.Inizializer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomInventoryManager {
    private static Inventory GUI1;
    private static Inventory GUI2;

    Set<Integer> occupiedsplots = new HashSet<Integer>();

    public CustomInventoryManager() {
        for(int i=0; i<=9; i++) {
            occupiedsplots.add(i);
        }
        occupiedsplots.add(17);
        occupiedsplots.add(18);
        occupiedsplots.add(26);
        occupiedsplots.add(27);
        occupiedsplots.add(35);
        occupiedsplots.add(36);
        for(int i=44; i<=53; i++) {
            occupiedsplots.add(i);
        }
        //GUI1 Manager
        GUI1 = org.bukkit.Bukkit.createInventory(new CustomInvHolder("GUI1"), 9*6, "§6CoreMines");
        ItemStack addButton = new NewMine().getNewMineButton();
        GUI1.setItem(48, addButton);
        ItemStack delButton = new DelMine().getDelMineButton();
        GUI1.setItem(50, delButton);

        List<ItemStack> mines = Inizializer.GetMines();
        for(int i=0; i<45; i++) {
            if(mines == null) {
                break;
            }
            if(mines.isEmpty()) {
                break;
            }
            if(!occupiedsplots.contains(i)) {
                GUI1.setItem(i, mines.get(0));
                mines.remove(0);
            }
        }

        //GUI2 Manager
        GUI2 = org.bukkit.Bukkit.createInventory(new CustomInvHolder("GUI2"), 9*6, "§cCoreMines");
        mines = Inizializer.GetMines();

        for(int i=0; i<45; i++) {
            if(mines == null) {
                break;
            }
            if(mines.isEmpty()) {
                break;
            }
            if(!occupiedsplots.contains(i)) {
                ItemStack item = mines.get(0);
                ItemMeta itemmeta = item.getItemMeta();
                if(itemmeta.getLore() == null) {
                    List<String> lores = new java.util.ArrayList<>();
                    lores.add("§cClicca per eliminare");
                    itemmeta.setLore(lores);
                    item.setItemMeta(itemmeta);
                    GUI2.setItem(i, item);
                    mines.remove(0);
                } else {
                    List<String> lores = itemmeta.getLore();
                    lores.add("§cClicca per eliminare");
                    itemmeta.setLore(lores);
                    item.setItemMeta(itemmeta);
                    GUI2.setItem(i, item);
                    mines.remove(0);
                }
            }

        }
        for(int i=45; i<54; i++) {
            ItemStack item = new ItemStack(org.bukkit.Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta itemmeta = item.getItemMeta();
            itemmeta.setDisplayName("&c&lMenu Eliminazione");
            item.setItemMeta(itemmeta);
            GUI2.setItem(i, item);
        }
    }

    public static Inventory getGUI1() {
        Core_Minesv2.updateCustomInvMan();
        return GUI1;
    }

    public static Inventory getGUI2() {
        Core_Minesv2.updateCustomInvMan();
        return GUI2;
    }


}
