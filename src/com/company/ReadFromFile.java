package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ReadFromFile implements Serializable {

    public static void DeserializeDemo() {
        Object object;
        try {
            FileInputStream fileIn = new FileInputStream("D:/Chats/newfile.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            object = in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Dank memes not found");
            c.printStackTrace();
            return;
        }

        System.out.println(object);

    }
}