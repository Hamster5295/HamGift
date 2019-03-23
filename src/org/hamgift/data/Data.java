package org.hamgift.data;

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
     * [0]disabled time
     *
     * */
    public static File infoFolder = new File("info.bin");
}
