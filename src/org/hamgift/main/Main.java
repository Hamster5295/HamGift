package org.hamgift.main;

import org.bukkit.plugin.java.JavaPlugin;
import org.hamgift.data.Data;
import org.hamgift.hgift.HGift;

import java.io.File;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private Logger log = getLogger();

    @Override
    public void onEnable() {
        log.info("Start loading HamGift");

        getDataFolder().mkdirs();       // Create Folder
        Data.dataFolder = getDataFolder();

        Data.gifts.forEach((k,v)->{
            v.load();
        });

        log.info("Loading completed.");
    }
}
