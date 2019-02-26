package com.company.MessageSendingClasses;

import com.company.ChatRooms.ChatRoom;
import com.company.ChatRooms.ChatRoomList;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ChatRoomListMessage implements Serializable {
    static final long serialVersionUID = 180;

    private Map<String,String> chatRoomOptions = new HashMap<>();

    public ChatRoomListMessage(){

    }

    public ChatRoomListMessage(Map<String, String> chatRoomOptions) {
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
