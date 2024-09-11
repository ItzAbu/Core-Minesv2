package org.itzabu.coreminesv2.GUIS.MainGui.Items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.itzabu.coreminesv2.Core_Minesv2;
import org.itzabu.coreminesv2.Utils.CustomHead;

public class MineResetButton {
    ItemStack MineResetButton;

    public MineResetButton() {
        //Testa personalizzata
        MineResetButton = new ItemStack(Material.BARRIER);
        ItemMeta addButtonMeta = MineResetButton.getItemMeta();
        addButtonMeta.setDisplayName("§cReset Miniera");

        //Valore Item
        NamespacedKey key = new NamespacedKey(Core_Minesv2.getInstance(), "resetmine");
        addButtonMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "resetmine");


        MineResetButton.setItemMeta(addButtonMeta);
    }

    public ItemStack getMineResetButton() {
        return MineResetButton;
    }
}
