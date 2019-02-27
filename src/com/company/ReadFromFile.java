package com.company;

import com.company.ChatRooms.ChatRoom;
import com.company.ChatRooms.ChatRoomList;

import java.io.*;

public class ReadFromFile implements Serializable {

    public static void LoadChatRooms() {
        File dir = new File("Chats/");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if (child.isFile()) {
                    try {
                        FileInputStream fileIn = new FileInputStream(child.getPath());
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        ChatRoom object = (ChatRoom) in.readObject();
                        System.out.println(object.getName());
                        ChatRoomList.createChatRoomFromStorage(object);
                        in.close();
                        fileIn.close();
                    } catch (IOException i) {
                        i.printStackTrace();
                    } catch (ClassNotFoundException c) {
                        c.printStackTrace();
                    }
                }
            }
        } else { 
            ChatRoomList.get().createChatRoom("General");
            ChatRoomList.get().createChatRoom("Study Room");
        }
    }
}