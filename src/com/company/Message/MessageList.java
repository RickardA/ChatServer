package com.company.Message;

import com.company.Message.Message;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageList implements Serializable{
    private ArrayList<Message> messagesList;
    static final long serialVersionUID = 30;

    public MessageList() {
        messagesList = new ArrayList<>();
    }

    public ArrayList<Message> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(Message message) {
        if (messagesList.size()<10){
            messagesList.add(message);
        }
        else{
            messagesList.remove(0);
            messagesList.add(message);
        }
    }
}



