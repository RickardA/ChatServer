package com.company;

import java.io.*;

public class SaveToFile implements Serializable {
    public static void SerializedDemo(Object object) {
        File Chats = new File("D:/Chats");

        if (!Chats.exists()) {
            new File("D:/Chats").mkdirs();
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("D:/Chats/newfile.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
            out.close();
            fileOut.close();
            System.out.printf("Serializable");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}

