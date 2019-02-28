package com.company.Message;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Message implements Serializable {

    private String message;
    private String senderName;
    private String timeStamp;
    private String userID;
    static final long serialVersionUID = 50;

    public Message() {
    }

    public Message createAdminMessage(String message){
        this.message = message + "\n";
        this.timeStamp = "";
        this.userID = "";
        this.senderName = "Admin";
        return this;
    }

    public String getUserID() {
        return userID;
    }
}
