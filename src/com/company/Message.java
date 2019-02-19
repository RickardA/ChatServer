package com.company;

import com.company.ChatRooms.ChatRoom;
import com.company.ChatRooms.ChatRoomList;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Message implements Serializable {

    private String message;
    private String senderName;
    private String timeStamp;
    private String channelID;
    static final long serialVersionUID = 50;


    public Message() {
    }

    public String getMessage() {
        return message;
    }


    public String getSenderName() {
        return senderName;
    }


    public String getTimeStamp() {
        return timeStamp;
    }

    public String getChannelID() {
        return channelID;
    }
}
