package com.company.User;

import java.io.Serializable;
import java.net.SocketAddress;
import java.util.UUID;

public class User implements Serializable {

    static final long serialVersionUID = 12;
    private String userName;
    private String userID;
    private String channelID;
    private SocketAddress userSocketAddress;


    public User(String name) {
        userName = name;
        //Creates a uniqe id and sets it to userID;
        userID = UUID.randomUUID().toString();
    }

    public String getUserName() {
        return userName;
    }

    public String getChannelID() {
        return channelID;
    }

    public String getUserID() {
        return userID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SocketAddress getUserSocketAddress() {
        return userSocketAddress;
    }

}


