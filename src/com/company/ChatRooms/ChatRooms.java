package com.company.ChatRooms;

import java.util.ArrayList;
import java.util.UUID;

public class ChatRooms {
    private ArrayList<ChatRoom> chatRoomList;

    public ChatRooms(){
        chatRoomList = new ArrayList<>();
        createChatRoom("General");
    }

    public void createChatRoom(String name){
        String uniqeID = UUID.randomUUID().toString();
        chatRoomList.add(new ChatRoom(name, uniqeID));
    }

    public ArrayList<ChatRoom> getChatRoomList() {
        return chatRoomList;
    }
}
