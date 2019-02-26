package com.company.MessageSendingClasses;

import com.company.User.User;

import java.io.Serializable;

public class ChatRoomIDMessage implements Serializable {
    static final long serialVersionUID = 190;
    private String chatRoomID;
    private User user;

    public ChatRoomIDMessage(String chatRoomID, User user){
        this.user = user;
        this.chatRoomID = chatRoomID;
    }

    public User getUser(){
        return user;
    }

    public String getChatRoomID(){
        return chatRoomID;
    }


}
