package org.hamgift.data;

import org.bukkit.ChatColor;
import org.hamgift.hgift.HGift;

import java.io.File;
import java.util.HashMap;

public class Data {

    //This class is used to save data from Input or custom gifts.

    public static HashMap<String, HGift> gifts = new HashMap<>();
    public static File dataFolder;

    /**
     * @Param infoFolder is used to save information
     * Read as a String Array.
     * Save as the format below:
     *
     * [0] disabled day in year
     *
     * */

    public static File infoFolder = new File("info.bin");

    public static String[] helpList = {
            ChatColor.GOLD+"======"+ChatColor.LIGHT_PURPLE+"HamGift 帮助"+ChatColor.GOLD+"======",
            ChatColor.GREEN+"/hg        打开帮助菜单",
            ChatColor.GREEN+"/hgift     打开帮助菜单"
    };
}
