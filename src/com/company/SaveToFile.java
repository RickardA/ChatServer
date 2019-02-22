package com.company;
import com.company.ChatRooms.ChatRoom;
import com.company.ChatRooms.ChatRoomList;

import java.io.*;

public class SaveToFile implements Serializable {
    public static void SerializedDemo() {
        File Chats = new File("D:/Chats");

        if (!Chats.exists()) {
            new File("D:/Chats").mkdirs();
        }
        for (ChatRoom chatroom : ChatRoomList.get().getChatRooms().values() ) {
            try {
                FileOutputStream fileOut = new FileOutputStream("D:/Chats/" + chatroom.getName());
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(chatroom.getChatHistory());
                out.close();
                fileOut.close();
                System.out.printf("Serializable");
            } catch (IOException i) {
                i.printStackTrace();
            }
        }

    }
}

