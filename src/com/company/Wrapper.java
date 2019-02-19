package com.company;

import com.company.ChatRooms.ChatRoom;
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

    public void collectChatRoomInfo(){
        for(Map.Entry<String, ChatRoom> chatRoom: ChatRoomList.get().getChatRooms().entrySet()){
            chatRoomOptions.put(chatRoom.getValue().getUniqeID(), chatRoom.getValue().getName());
        }
    }

    public Map<String, String> getChatRoomOptions() {
        return chatRoomOptions;
    }
}
