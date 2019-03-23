package org.hamgift.main;

import org.bukkit.plugin.java.JavaPlugin;
import org.hamgift.data.Data;
import org.hamgift.hgift.HGift;
import org.hamgift.utils.Input;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private Logger log = getLogger();

    @Override
    public void onEnable() {
        log.info("Start loading HamGift");

        setFiles();
        try {
            readFiles();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        log.info("Loading completed.");
    }

    private void setFiles(){
        getDataFolder().mkdirs();       // Create Folder
        Data.dataFolder = getDataFolder();
        Data.infoFolder = new File(getDataFolder().toString()+File.separator+Data.infoFolder.toString());
    }

    private void readFiles() throws IOException, ClassNotFoundException {
        File[] files = getDataFolder().listFiles();
        Thread thread = new Thread(()->{
            Data.gifts.forEach((k,gift)-> gift.upDate());
        });

        for (File file: files
             ) {
            String fn = file.getName();
            switch (fn){
                case "info.bin":
                    String[] buff = (String[]) Input.readNormal(file);
                    long last = Integer.parseInt(buff[0]);
                    if((System.currentTimeMillis() - last) >= 24*3600){
                        thread.start();
                        thread.interrupt();
                    }
                    break;

                default:
                    if(fn.endsWith("_HG.bin")){
                        Data.gifts.put(fn.split("_")[0],(HGift) Input.readNormal(file));
                    }
            }

            thread.notify();
        }
    }
}
