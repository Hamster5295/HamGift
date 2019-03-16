package org.hamgift.utils;

import java.io.*;

public class Output {

    /**
     * @Params
     * This class mainly includes static methods about writing files
     * */

    public static void writeNormal(File file, Object what) throws IOException {

        if(!file.exists()) return;
        if(file.isDirectory()) return;

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(what);
    }
}
