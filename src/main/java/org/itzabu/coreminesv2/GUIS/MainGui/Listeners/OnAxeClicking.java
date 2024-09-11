package org.itzabu.coreminesv2.GUIS.MainGui.Listeners;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.itzabu.coreminesv2.Core_Minesv2;
import org.itzabu.coreminesv2.CustomConfigs.customConfig;

public class OnAxeClicking implements Listener {

    int x1, y1, z1, x2, y2, z2;

    @EventHandler
    public void onAxeClick(BlockBreakEvent e) {

        if(!Core_Minesv2.isOnMineCreation()) {
            return;
        }
        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();



        ItemMeta itemmeta = item.getItemMeta();
        if(itemmeta == null) {
            return;
        }
        if(itemmeta.getPersistentDataContainer().has(new NamespacedKey(Core_Minesv2.getInstance(), "Selector"), PersistentDataType.STRING)) {
            x1 = e.getBlock().getX();
            y1 = e.getBlock().getY();
            z1 = e.getBlock().getZ();
            e.getPlayer().sendMessage("§aPosizione 1 selezionata: X: " + x1 + " Y: " + y1 + " Z: " + z1);



            e.setCancelled(true);
        }


    }


    @EventHandler
    public void onAxePlace(PlayerInteractEvent e) {
        if(!Core_Minesv2.isOnMineCreation()) {
            return;
        }
        if(e.getAction().isLeftClick()) {
            return;
        }
        if(e.getItem() == null) {
            return;
        }

        if(e.getAction().isRightClick()) {
            if(e.getClickedBlock() == null || e.getClickedBlock().getType() == Material.AIR ) {
                return;
            }
            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
            ItemMeta itemmeta = item.getItemMeta();
            if(itemmeta == null) {
                return;
            }
            if(itemmeta.getPersistentDataContainer().has(new NamespacedKey(Core_Minesv2.getInstance(), "Selector"), PersistentDataType.STRING)) {
                x2 = e.getClickedBlock().getX();
                y2 = e.getClickedBlock().getY();
                z2 = e.getClickedBlock().getZ();
                e.getPlayer().sendMessage("§aPosizione 2 selezionata: X: " + x2 + " Y: " + y2 + " Z: " + z2);


                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onText(AsyncPlayerChatEvent e) {
        if(!Core_Minesv2.isOnMineCreation()) {
            return;
        }
        e.setCancelled(true);
        if(e.getMessage().equalsIgnoreCase("fatto")) {
            e.getPlayer().sendMessage("§aMiniera creata con successo");
            Core_Minesv2.onMineCreationChange();
            int mines = customConfig.minesCount()+1;

            customConfig.newMine(x1, y1, z1, x2, y2, z2, "Mine_" + mines, e.getPlayer().getWorld());
            Core_Minesv2.usingchange();
            Inventory inv = customConfig.getInv(e.getPlayer().getUniqueId());
            for(int i=0; i<36; i++) {
                e.getPlayer().getInventory().setItem(i, inv.getItem(i));
            }
            e.getPlayer().getInventory().setArmorContents(new ItemStack[] {inv.getItem(36), inv.getItem(37), inv.getItem(38), inv.getItem(39)} );
            e.getPlayer().getInventory().setItemInOffHand(inv.getItem(40));
            e.getPlayer().sendMessage("&eInventario Restituito con successo");
        }else {
            e.getPlayer().sendMessage("§cNon hai scritto 'fatto' per confermare la creazione della miniera");
            Core_Minesv2.usingchange();
            Inventory inv = customConfig.getInv(e.getPlayer().getUniqueId());
            for(int i=0; i<36; i++) {

                e.getPlayer().getInventory().setItem(i, inv.getItem(i));
            }
            e.getPlayer().getInventory().setArmorContents(new ItemStack[] {inv.getItem(36), inv.getItem(37), inv.getItem(38), inv.getItem(39)} );
            e.getPlayer().getInventory().setItemInOffHand(inv.getItem(40));
            e.getPlayer().sendMessage("&eInventario Restituito con successo");

        }
    }
}
