package org.itzabu.coreminesv2.GUIS.MainGui.Items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.itzabu.coreminesv2.Core_Minesv2;
import org.itzabu.coreminesv2.Utils.CustomHead;

public class DelMine {

    ItemStack DelMine;

    public DelMine() {
        //Testa personalizzata
        String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjNhZjhhZmRhM2E4YjUzZDAxZTA5MTA4OGJlZjljMGU3Y2NhMDk1MDE1YTE4OWU5NzI2NzE0YmQ0YzczZmNiMSJ9fX0=";
        DelMine = CustomHead.getCustomHead(base64);
        ItemMeta delButtonMeta = DelMine.getItemMeta();
        delButtonMeta.setDisplayName("§cElimina Miniera");

        //Valore Item
        NamespacedKey key = new NamespacedKey(Core_Minesv2.getInstance(), "delmine");
        delButtonMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "delmine");


        DelMine.setItemMeta(delButtonMeta);

    }

    public ItemStack getDelMineButton() {
        return DelMine;
    }
}
