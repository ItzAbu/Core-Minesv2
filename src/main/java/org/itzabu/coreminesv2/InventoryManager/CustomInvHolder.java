package org.itzabu.coreminesv2.InventoryManager;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class CustomInvHolder implements InventoryHolder {
    private String identifier;

    public CustomInvHolder(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }


    @Override
    public @NotNull Inventory getInventory() {
        return null;
    }
}
