package org.itzabu.coreminesv2.GUIS.MainGui.Items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.itzabu.coreminesv2.Core_Minesv2;
import org.itzabu.coreminesv2.Utils.CustomHead;

public class NewMine {
    ItemStack NewMineButton;

    public NewMine() {
        //Testa personalizzata
        String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDM2ZjBlOTJiMmMzNmM2NGNhNmNlOTgyNzIwOTAzNjFmMjllMzk1NDBmMmE2ZTRhZDI2NGFiNDJhMmFjNDlhZCJ9fX0=";
        NewMineButton = CustomHead.getCustomHead(base64);
        ItemMeta addButtonMeta = NewMineButton.getItemMeta();
        addButtonMeta.setDisplayName("§aNuova Miniera");

        //Valore Item
        NamespacedKey key = new NamespacedKey(Core_Minesv2.getInstance(), "addmine");
        addButtonMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "addmine");


        NewMineButton.setItemMeta(addButtonMeta);
    }

    public ItemStack getNewMineButton() {
        return NewMineButton;
    }

}
