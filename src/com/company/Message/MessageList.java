package com.company.Message;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageList implements Serializable{
    private ArrayList<Message> messagesList;
    private Message welcomeMessage;
    static final long serialVersionUID = 30;

    public MessageList() {
        messagesList = new ArrayList<>();
    }

    public void createWelcomeMessage(String chatRoomName){
        Message message = new Message();
        welcomeMessage = (message.createAdminMessage("Welcome to " + chatRoomName + ", be nice!"));
    }

    public ArrayList<Message> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(Message message) {
            messagesList.add(message);
    }
}



