package org.hamgift.data;

import org.bukkit.inventory.Inventory;

public class HGift {

    private String name;
    private Inventory inv;

    public void HGift(String name,Inventory inv){
        this.name = name;
        this.inv = inv;

        //The gift won't be created if the name is already used.
        Data.gifts.putIfAbsent(name,this);
    }
}
