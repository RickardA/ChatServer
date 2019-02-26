package com.company;
import com.company.ChatRooms.ChatRoom;
import com.company.ChatRooms.ChatRoomList;

import java.io.*;

public class SaveToFile implements Serializable {
    public static void SerializeToFile() {
        String ChatRooms = System.getProperty("user.dir"); //makes so the folder is created in ChatServer, makes the creation universal.
        File Chats = new File("ChatRooms");

        if (!Chats.exists()) { //creates a Chats folder i none exists already
            new File("Chats/").mkdirs();
        }
        for (ChatRoom chatroom : ChatRoomList.get().getChatRooms().values() ) {
            try {
                FileOutputStream fileOut = new FileOutputStream("Chats/" + chatroom.getName()); 
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(chatroom);
                out.close();
                fileOut.close();
                //System.out.printf("Serializable"); Not needed. Only there for testing
            } catch (IOException i) {
                i.printStackTrace();
            }
        }

    }
}

