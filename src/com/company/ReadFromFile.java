package com.company;

import com.company.ChatRooms.ChatRoom;
import com.company.ChatRooms.ChatRoomList;

import java.io.*;

public class ReadFromFile implements Serializable {

    public void loadChatRooms(ChatRoomList chatRoomList) {
        File dir = new File("Chats/");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if (child.isFile()) {
                    try {
                        FileInputStream fileIn = new FileInputStream(child.getPath());
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        ChatRoom object = (ChatRoom) in.readObject();
                        chatRoomList.createChatRoomFromStorage(object);
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
            chatRoomList.createChatRoom("General");
            chatRoomList.createChatRoom("Study Room");
        }
    }
}