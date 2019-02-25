package com.company;

import com.company.ChatRooms.ChatRoom;
import com.company.ChatRooms.ChatRoomList;

import java.io.*;

public class ReadFromFile implements Serializable {

    public static void DeserializeDemo() {
        Object object;
        for (ChatRoom chatroom : ChatRoomList.get().getChatRooms().values()) {
            File dir = new File("D:/Chats/");
            File f = new File("C:/Chats/" + chatroom.getName());
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null){
                for (File child : directoryListing){
                    if (f.getName().equals(chatroom.getName())){
                        try {
                            FileInputStream fileIn = new FileInputStream("D:/Chats/" + chatroom.getName()); //Behöver bytas ut till universial path
                            ObjectInputStream in = new ObjectInputStream(fileIn);
                            object = in.readObject();
                            in.close();
                            fileIn.close();
                        } catch (IOException i) {
                            i.printStackTrace();
                            return;
                        } catch (ClassNotFoundException c) {
                            c.printStackTrace();
                            return;
                        }
                    }
                }
            }
            /*try {
                FileInputStream fileIn = new FileInputStream("D:/Chats/" + chatroom.getName()); //Behöver bytas ut till universial path
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
            }*/
        }

    }
}