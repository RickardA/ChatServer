package com.company.Message;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Message implements Serializable {

    private String message;
    private String senderName;
    private String timeStamp;
    private String channelID;
    static final long serialVersionUID = 50;

    //Creates Admin message
    public Message(String message) {
        this.message = message + "\n";
        this.timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        this.channelID = "";
        this.senderName = "Admin";
    }

    public String getChannelID() {
        return channelID;
    }
}
