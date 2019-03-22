package org.hamgift.hgift;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.hamgift.data.Data;
import org.hamgift.utils.InvUtils;
import org.hamgift.utils.Output;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import static org.hamgift.utils.Input.readNormal;

public class HGift implements Serializable {

    private String name;
    private Inventory inv;
    private ArrayList<UUID> got;
    private GiftType type;
    private File f;             //Save path

    public void HGift(String name,Inventory inv,GiftType type){
        this.name = name;
        this.inv = inv;
        this.type = type;

        f = new File(Data.dataFolder+name+".bin");

        try {
            Output.writeNormal(f,this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Data.gifts.replace(name,this);
    }

    public void load(){
        switch (type){
            case ONLY_ONCE:
                try {
                    this.got = (ArrayList<UUID>) readNormal(f);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;

            case DAILY:

                break;

            case WEEKLY:

                break;
        }
    }

    public void give(Player p) {
        if (!p.isOnline()) return;
        if(!p.isValid()) return;

        switch (type){
            case ONLY_ONCE:
                if(!p.hasPermission("hgift.getOnce")) return;
                if(got.contains(p.getUniqueId())) return;

                Inventory pi = p.getInventory();

                if(pi.getMaxStackSize() - pi.getSize() < InvUtils.getItems(pi).size()){
                    p.sendMessage(ChatColor.RED+"背包没有足够的空间!");
                    return;
                }else {
                    pi.addItem((ItemStack[]) InvUtils.getItems(pi).toArray());
                }
                break;
        }
    }
}
