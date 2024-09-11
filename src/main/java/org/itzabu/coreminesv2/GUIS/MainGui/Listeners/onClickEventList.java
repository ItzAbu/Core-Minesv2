package org.itzabu.coreminesv2.GUIS.MainGui.Listeners;

import jdk.internal.net.http.common.Pair;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.itzabu.coreminesv2.Core_Minesv2;
import org.itzabu.coreminesv2.CustomConfigs.customConfig;
import org.itzabu.coreminesv2.GUIS.MainGui.Items.AxeSelector;
import org.itzabu.coreminesv2.GUIS.MainGui.Items.DelMine;
import org.itzabu.coreminesv2.InventoryManager.CustomInvHolder;
import org.itzabu.coreminesv2.InventoryManager.CustomInventoryManager;
import org.itzabu.coreminesv2.InventoryManager.DinamicInvMine;
import org.itzabu.coreminesv2.Utils.ResetFunction;

import java.util.List;
import java.util.Objects;

public class onClickEventList implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(!(e.getInventory().getHolder() instanceof CustomInvHolder)) {
            return;
        }
        if(Objects.equals(((CustomInvHolder) e.getInventory().getHolder()).getIdentifier(), "GUI1")) {
            ItemStack clickedItem = e.getCurrentItem();
            if(clickedItem == null) {
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);
            ItemMeta itemMetaClicked = clickedItem.getItemMeta();

            //NewMine Button pressed
            if(itemMetaClicked.getPersistentDataContainer().has(new NamespacedKey(Core_Minesv2.getInstance(), "addmine"), PersistentDataType.STRING)) {
                e.getWhoClicked().sendMessage("§aSeleziona i 2 angoli della miniera e una volta completato scrivi 'fatto' \n\n §cATTENZIONE: §7Se non scrivi 'fatto' oppure esci dal gioco la miniera non verrà creata e perderai l'inventario");
                ItemStack SelectorAxe = AxeSelector.getAxeSelectorItem();
                customConfig.newInv(e.getWhoClicked().getInventory(),e.getWhoClicked().getInventory().getArmorContents(),e.getWhoClicked().getInventory().getItemInOffHand(), e.getWhoClicked().getUniqueId());
                e.getWhoClicked().getInventory().clear();
                e.getWhoClicked().getInventory().addItem(SelectorAxe);
                Core_Minesv2.onMineCreationChange();
                e.getInventory().close();
                return;
            }
            //DelMine Button pressed
            if(itemMetaClicked.getPersistentDataContainer().has(new NamespacedKey(Core_Minesv2.getInstance(), "delmine"), PersistentDataType.STRING)) {
                Inventory inv = CustomInventoryManager.getGUI2();
                e.getInventory().close();
                e.getWhoClicked().openInventory(inv);
                return;
            }
            //MinePressed
            String minename = itemMetaClicked.getDisplayName();
            String result = DinamicInvMine.getDinInv(minename);

            if(Objects.equals(result, "")) {
                e.getInventory().close();
                e.getWhoClicked().openInventory(DinamicInvMine.openDinInv(minename, e.getWhoClicked().getUniqueId()));

            }
            else {
                e.getWhoClicked().sendMessage("§cErrore: "+ result);
                return;
            }


        }else if(Objects.equals(((CustomInvHolder) e.getInventory().getHolder()).getIdentifier(), "GUI2")) {
            ItemStack clickedItem = e.getCurrentItem();
            if(clickedItem == null || clickedItem.equals(new ItemStack(Material.RED_STAINED_GLASS_PANE))) {
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);
            ItemMeta itemMetaClicked = clickedItem.getItemMeta();
            customConfig.delMine(itemMetaClicked.getDisplayName());
            Inventory inv = CustomInventoryManager.getGUI1();
            e.getInventory().close();
            e.getWhoClicked().openInventory(inv);
            e.getWhoClicked().sendMessage("§aMiniera"+ itemMetaClicked.getDisplayName() +" eliminata con successo");
            System.out.println("Miniera "+ itemMetaClicked.getDisplayName() +" eliminata con successo");

        }else if(Objects.equals(((CustomInvHolder) e.getInventory().getHolder()).getIdentifier(), e.getWhoClicked().getUniqueId().toString())) {
            ItemStack clickedItem = e.getCurrentItem();
            if(clickedItem == null) {
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);
            ItemMeta itemMetaClicked = clickedItem.getItemMeta();
            if(itemMetaClicked.getPersistentDataContainer().has(new NamespacedKey(Core_Minesv2.getInstance(), "resetmine"), PersistentDataType.STRING)) {
                List<Location> locs = customConfig.getLocations(e.getClickedInventory().getItem(13).getItemMeta().getDisplayName());
                ResetFunction.resetMine(locs.get(0).getWorld(), locs.get(0), locs.get(1), Material.STONE);
                e.getWhoClicked().sendMessage("§aMiniera "+ itemMetaClicked.getDisplayName() +" resettata con successo");
                e.setCancelled(true);
                e.getInventory().close();
                return;
            }
        }



    }

}
