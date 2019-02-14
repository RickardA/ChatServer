package com.company.ChatRooms;

import com.company.NetworkServer;
import com.company.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class ChatRooms implements Serializable{
    private ArrayList<ChatRoom> chatRoomList;
    static final long serialVersionUID = 30;


    public ChatRooms() {
        chatRoomList = new ArrayList<>();
        createChatRoom("General");
    }

    public void createChatRoom(String name) {
        String uniqeID = UUID.randomUUID().toString();
        chatRoomList.add(new ChatRoom(name, uniqeID));
    }

    public void sendChatRoomsToClient() {
        for (User user : NetworkServer.get().usersConnected) {
            System.out.println("Sending shit to client");
            System.out.println(user.getUserName());
            NetworkServer.get().sendObjectToClient( chatRoomList, user.getUserSocketAddress());
        }
    }

    public ArrayList<ChatRoom> getChatRoomList() {
        return chatRoomList;
    }
}
