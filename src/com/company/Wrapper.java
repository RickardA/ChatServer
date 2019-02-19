package com.company;

import com.company.ChatRooms.ChatRoomList;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Wrapper implements Serializable {
    Map<String,String> chatRoomOptions = new HashMap<>();

    public Wrapper() {
    }

    public Wrapper(Map<String, String> chatRoomOptions) {
        this.chatRoomOptions = chatRoomOptions;
    }

    public void collectChatRoomInfo( ){
        ChatRoomList.get().getChatRooms().forEach(chatRoom -> chatRoomOptions.put(chatRoom.getUniqeID(), chatRoom.getName()));
        //NetworkServer.get().sendObjectToClient(chatRoomsListName,sendingClientsAdress );
    }

    public Map<String, String> getChatRoomOptions() {
        return chatRoomOptions;
    }
}
