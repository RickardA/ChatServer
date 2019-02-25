package com.company;

import com.company.ChatRooms.ChatRoom;
import com.company.ChatRooms.ChatRoomList;
import com.company.User.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Wrapper implements Serializable {
    private Map<String,String> chatRoomOptions = new HashMap<>();
    static final long serialVersionUID = 80;
    private String chatRoomID;
    private User user;

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
    public Wrapper (String chatRoomID,User user){
        this.user = user;
        this.chatRoomID = chatRoomID;
    }

    public String getChatRoomID(){
        return chatRoomID;
    }

    public User getUser(){
        return user;
    }

    public Map<String, String> getChatRoomOptions() {
        return chatRoomOptions;
    }
}
