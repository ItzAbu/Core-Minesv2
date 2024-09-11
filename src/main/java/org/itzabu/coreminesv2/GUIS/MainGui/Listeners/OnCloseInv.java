package org.itzabu.coreminesv2.GUIS.MainGui.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.itzabu.coreminesv2.InventoryManager.CustomInvHolder;
import org.itzabu.coreminesv2.InventoryManager.DinamicInvMine;

import java.util.Objects;

public class OnCloseInv implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e) {

        DinamicInvMine.closeDinInv((Player) e.getPlayer());
    }
}
