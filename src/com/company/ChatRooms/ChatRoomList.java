package com.company.ChatRooms;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatRoomList implements Serializable {
    private Map<String,ChatRoom> chatRoomList;
    static final long serialVersionUID = 30;

    public ChatRoomList() {
        chatRoomList = new HashMap<String,ChatRoom>();
    }

    public Map<String, ChatRoom> getChatRooms() {
        return chatRoomList;
    }

    public void createChatRoom(String name) {
        String uniqueID = UUID.randomUUID().toString();
        chatRoomList.put(uniqueID,new ChatRoom(name, uniqueID));
    }

    public void createChatRoomFromStorage(ChatRoom chatRoom){
        chatRoom.getUsersOnlineList().resetUserOnlineList();
        chatRoomList.put(chatRoom.getUniqeID(),chatRoom);
    }
}
