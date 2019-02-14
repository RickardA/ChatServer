package com.company.ChatRooms;

import com.company.NetworkServer;
import com.company.User;

import java.util.ArrayList;
import java.util.UUID;

public class ChatRooms {
    private ArrayList<ChatRoom> chatRoomList;

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
            NetworkServer.get().sendObjectToClient(chatRoomList, user.getUserSocketAddress());
        }
    }

    public ArrayList<ChatRoom> getChatRoomList() {
        return chatRoomList;
    }
}
