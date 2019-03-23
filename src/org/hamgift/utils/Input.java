package org.hamgift.utils;

import java.io.*;

public class Input {

    /**
     * @Params
     * This class mainly includes static methods about reading files
     * */

    public static Object readNormal(File file) throws IOException, ClassNotFoundException {

        if(file.isDirectory()) return null;
        if(!file.exists())
            file.createNewFile();

        if(file.length() != 0){
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            return in.readObject();
        }else {
            return null;
        }

    }
}
