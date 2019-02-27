package com.company;

import com.company.ChatRooms.ChatRoom;
import com.company.ChatRooms.ChatRoomList;

import java.io.*;

public class ReadFromFile implements Serializable {

    public static void LoadChatRooms() {
        // for (ChatRoom chatroom : ChatRoomList.get().getChatRooms().values()) {
        String ChatRooms = System.getProperty("user.dir");
        File dir = new File("Chats/");
        File[] directoryListing = dir.listFiles(); //Checks files in Chats folder
        if (directoryListing != null) { //checks so the directory where the chatrooms are in is not empty
            for (File child : directoryListing) {  //checks each item in the folder
                if (child.isFile()) { //checks if the item is a file
                    try {
                        FileInputStream fileIn = new FileInputStream(child.getPath()); //Behöver bytas ut till universial path
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        ChatRoom object = (ChatRoom) in.readObject(); //reads in the chatroom from file
                        System.out.println(object.getName()); // prints out the chatroom in console to check if it works. Not needed.
                        ChatRoomList.createChatRoomFromStorage(object); //Takes Chatroom from file and sends it to createChatRoomFromStorage where it resets the user list and creates the chat room.
                        in.close();
                        fileIn.close();
                    } catch (IOException i) {
                        i.printStackTrace();
                    } catch (ClassNotFoundException c) {
                        c.printStackTrace();
                    }
                }
            }
        } else { //if there are no chatrooms in file then this creates the 2 standard chatrooms
            ChatRoomList.get().createChatRoom("General");
            ChatRoomList.get().createChatRoom("Study Room");
        }
    }
}