package org.hamgift.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.hamgift.commandexecuter.CE_hgift;
import org.hamgift.data.Data;
import org.hamgift.hgift.HGift;
import org.hamgift.thread.DateCheckerThread;
import org.hamgift.utils.Input;
import org.hamgift.utils.Output;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private Logger log = getLogger();
    private DateCheckerThread DCTread = new DateCheckerThread(3600*1000);

    @Override
    public void onEnable() {
        log.info("Start loading HamGift");

        setFiles();
        try {
            readFiles();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.getCommand("hg").setExecutor(new CE_hgift(this));
        this.getCommand("hgift").setExecutor(new CE_hgift(this));

        log.info("Loading completed.");
    }

    @Override
    public void onDisable() {
        log.info("Start disabling HamGift");

        saveFiles();

        log.info("Done");
    }

    private void setFiles(){
        getDataFolder().mkdirs();       // Create Folder
        Data.dataFolder = getDataFolder();
        Data.infoFolder = new File(getDataFolder().toString()+File.separator+Data.infoFolder.toString());
    }

    private void readFiles() throws IOException, ClassNotFoundException {
        File[] files = getDataFolder().listFiles();
        boolean flag = false;

        for (File file: files
             ) {
            String fn = file.getName();
            switch (fn){
                case "info.bin":
                    String[] buff = (String[]) Input.readNormal(file);
                    DCTread.setBefore(Integer.parseInt(buff[0]));
                    break;

                default:
                    if(fn.endsWith("_HG.bin")){
                        Data.gifts.put(fn.split("_")[0],(HGift) Input.readNormal(file));
                    }
                    break;
            }
        }

        DCTread.start();
    }

    private void saveFiles() {
        Calendar c = Calendar.getInstance();
        String[] info = {
                c.get(Calendar.DAY_OF_YEAR)+""
        };
        try {
            Output.writeNormal(Data.infoFolder,info);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Data.gifts.forEach((k, gift)-> {
            try {
                Output.writeNormal(gift.getFile(),gift);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
