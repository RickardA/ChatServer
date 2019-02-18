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

    public Message(String message, String senderName) {
        this.message = message;
        this.senderName = senderName;
        this.timeStamp = new SimpleDateFormat("yy-MM-dd HH:mm").format(Calendar.getInstance().getTime());;
        this.channelID = ChatRoomList.get().getChatRooms().get(0).getUniqeID();
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
