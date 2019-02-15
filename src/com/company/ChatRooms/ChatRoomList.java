package com.company.ChatRooms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class ChatRoomList implements Serializable {
    private ArrayList<ChatRoom> chatRoomList;
    private static ChatRoomList _singleton = new ChatRoomList();
    static final long serialVersionUID = 30;

    public ChatRoomList() {
        chatRoomList = new ArrayList<>();
    }

    public static ChatRoomList get() {
        return _singleton;
    }

    public ArrayList<ChatRoom> getChatRooms() {
        return chatRoomList;
    }

    public void createChatRoom(String name) {
        String uniqeID = UUID.randomUUID().toString();
        chatRoomList.add(new ChatRoom(name, uniqeID));
    }

    public void displayChatRooms() {

    }

    public void updateChatRoomList (ArrayList<ChatRoom> chatRoomList) {
        this.chatRoomList = chatRoomList;
    }
}
