package com.company.Message;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageList implements Serializable{
    private ArrayList<Message> messagesList;
    static final long serialVersionUID = 30;

    public MessageList() {
        messagesList = new ArrayList<>();
    }

    public void createAndAddWelcomeMessageToChatRoom(String chatRoomName){
        Message message = new Message();
        messagesList.add(message.createAdminMessage("Welcome to " + chatRoomName + ", be nice!"));
    }

    public ArrayList<Message> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(Message message) {
            messagesList.add(message);
    }
}



