package com.company.MessageSendingClasses;

import com.company.ChatRooms.ChatRoom;
import com.company.ChatRooms.ChatRoomList;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ChatRoomNamesMessage implements Serializable {
    static final long serialVersionUID = 180;

    private Map<String,String> chatRoomNames = new HashMap<>();

    public ChatRoomNamesMessage(){

    }

    public ChatRoomNamesMessage(Map<String, String> chatRoomNames) {
        this.chatRoomNames = chatRoomNames;
    }
    public void collectChatRoomInfo(){
        for(Map.Entry<String, ChatRoom> chatRoom: ChatRoomList.get().getChatRooms().entrySet()){
            chatRoomNames.put(chatRoom.getValue().getUniqeID(), chatRoom.getValue().getName());
        }
    }

    public Map<String, String> getChatRoomNames() {
        return chatRoomNames;
    }

}
