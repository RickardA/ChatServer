package com.company.ChatRooms;

import com.company.NetworkServer;
import com.company.Tuple;
import com.company.User;

import java.io.Serializable;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.UUID;

public class ChatRooms implements Serializable{
    private ArrayList<ChatRoom> chatRoomList;
    static final long serialVersionUID = 30;
    private static ChatRooms _singleton = new ChatRooms();

    public ChatRooms() {
        chatRoomList = new ArrayList<>();
        createChatRoom("General");
        createChatRoom("Ramis Happy Place");
    }

    public void createChatRoom(String name) {
        String uniqeID = UUID.randomUUID().toString();
        chatRoomList.add(new ChatRoom(name, uniqeID));
    }

    public static ChatRooms get(){
        return _singleton;
    }

    public void sendChatRoomsToClient(SocketAddress sendingClientsAdress) {
            NetworkServer.get().sendObjectToClient( chatRoomList, sendingClientsAdress);
    }

    public ArrayList<ChatRoom> getChatRoomList() {
        return chatRoomList;
    }
}
