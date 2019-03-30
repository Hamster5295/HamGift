package org.hamgift.commandexecuter;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.hamgift.data.Data;

public class CE_hgift implements CommandExecutor {

    private JavaPlugin plugin;

    public CE_hgift(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command c, String s, String[] args) {
        if(!(cs instanceof Player)){
            cs.sendMessage("控制台不能使用该指令!");
            return false;
        }

        Player p = (Player) cs;

        if(args.length == 1){
            p.sendMessage(Data.helpList);
            return true;
        }

        if(args.length == 2){

        }

        return false;
    }

}
