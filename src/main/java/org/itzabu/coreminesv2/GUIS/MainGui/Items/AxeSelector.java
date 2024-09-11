package org.itzabu.coreminesv2.GUIS.MainGui.Items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.itzabu.coreminesv2.Core_Minesv2;

public class AxeSelector {
    static ItemStack AxeSelectorItem;

    public AxeSelector() {
        //Item
        AxeSelectorItem = new ItemStack(Material.GOLDEN_AXE);
        AxeSelectorItem.addUnsafeEnchantment(Enchantment.FROST_WALKER, 1);
        ItemMeta selectorAxeMeta = AxeSelectorItem.getItemMeta();
        selectorAxeMeta.setDisplayName("§aSelettore");

        //Valore Item
        NamespacedKey key = new NamespacedKey(Core_Minesv2.getInstance(), "Selector");
        selectorAxeMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "Selector");

        AxeSelectorItem.setItemMeta(selectorAxeMeta);
    }

    public static ItemStack getAxeSelectorItem() {
        return AxeSelectorItem;
    }

}
