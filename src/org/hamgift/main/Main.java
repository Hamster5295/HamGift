package org.hamgift.main;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private Logger log = getLogger();

    @Override
    public void onEnable() {
        log.info("Start loading HamGift");

        getDataFolder().mkdirs();       // Create Folder


        log.info("Loading completed.");
    }
}
