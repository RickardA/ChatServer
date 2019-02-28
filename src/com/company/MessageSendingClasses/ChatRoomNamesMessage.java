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

    public void collectChatRoomInfo(ChatRoomList chatRoomList){
        for(Map.Entry<String, ChatRoom> chatRoom: chatRoomList.getChatRooms().entrySet()){
            chatRoomNames.put(chatRoom.getValue().getUniqeID(), chatRoom.getValue().getName());
        }
    }

}
