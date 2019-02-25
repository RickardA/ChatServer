package com.company;

import com.company.ChatRooms.ChatRoom;
import com.company.ChatRooms.ChatRoomList;

import java.io.*;

public class ReadFromFile implements Serializable {

    public static void DeserializeDemo() {
        ChatRoom object;
       // for (ChatRoom chatroom : ChatRoomList.get().getChatRooms().values()) {
            File dir = new File("D:/Chats/");
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null){
                for (File child : directoryListing){
                    if (child.isFile()){
                        try {
                            FileInputStream fileIn = new FileInputStream(child.getPath()); //Behöver bytas ut till universial path
                            ObjectInputStream in = new ObjectInputStream(fileIn);
                            object = (ChatRoom)in.readObject();
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