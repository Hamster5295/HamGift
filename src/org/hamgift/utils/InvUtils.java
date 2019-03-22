package org.hamgift.utils;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class InvUtils {

    public static ArrayList<ItemStack> getItems(Inventory inv){
        ArrayList<ItemStack> itemStacks = new ArrayList<>();

        for (ItemStack item: inv.getContents()
             ) {
            if(item != null && !item.getType().equals(Material.AIR))
                itemStacks.add(item);
        }

        return itemStacks;
    }
}
