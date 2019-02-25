package com.company.Message;

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


    public Message(String message,String channelID) {
        this.message = message;
        //this.senderName = ClientProgram.get().getUser().getUserName();
        this.timeStamp = new SimpleDateFormat("yy-MM-dd HH:mm").format(Calendar.getInstance().getTime());
        this.channelID = channelID;
    }

    public Message(String message) {
        this.message = message + "\n";
        this.timeStamp = "";
        this.channelID = "";
        this.senderName = "Admin";
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
