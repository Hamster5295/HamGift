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
    private String permission;
    private int timer;
    private File f;             //Save path

    public void HGift(String name,Inventory inv,GiftType type,String permission){
        if(type.equals(GiftType.TIMER)){
            throw new NullPointerException("Cannot find the timer!");
        }

        this.name = name;
        this.inv = inv;
        this.type = type;
        this.permission = permission;

        this.f = new File(Data.dataFolder+File.separator+name+"_HG.bin");

        try {
            Output.writeNormal(f,this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Data.gifts.replace(name,this);
    }

    public void HGift(String name,Inventory inv,GiftType type,String permission,int time){
        if(type.equals(GiftType.TIMER)){
            this.timer = time;
        }

        this.name = name;
        this.inv = inv;
        this.type = type;
        this.permission = permission;

        this.f = new File(Data.dataFolder+File.separator+name+"_HG.bin");

        try {
            Output.writeNormal(f,this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Data.gifts.replace(name,this);
    }

    public void give(Player p) {
        Inventory pi = p.getInventory();

        if (!p.isOnline()) return;
        if(!p.isValid()) return;

        if(!p.hasPermission(permission)) {
            p.sendMessage(ChatColor.RED+"你没有权限领取哦..");
            return;
        }

        if(got.contains(p.getUniqueId())){
            p.sendMessage(ChatColor.RED+"你已经领取过了..");
            return;
        }

        if(pi.getMaxStackSize() - pi.getSize() < InvUtils.getItems(pi).size()){
            p.sendMessage(ChatColor.RED+"背包没有足够的空间!");
            return;
        }

        pi.addItem((ItemStack[]) InvUtils.getItems(pi).toArray());
        got.add(p.getUniqueId());
    }

    public void upDate(){
        switch (type){
            case ONLY_ONCE:
                break;

            case DAILY:
                got.clear();
                break;

            case TIMER:
                if(timer==0)
                    got.clear();
                break;
        }
    }
}
