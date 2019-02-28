package com.company.User;

import java.io.Serializable;
import java.net.SocketAddress;
import java.util.UUID;

public class User implements Serializable {

    static final long serialVersionUID = 12;
    private String userName;
    private String userID;
    private String channelID;
    private transient String password;
    private SocketAddress userSocketAddress;


    public User(String name,String password) {
        this.userName = name;
        this.password = password;
        this.userID = UUID.randomUUID().toString();
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

    public String getPassword() {
        return password;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public SocketAddress getUserSocketAddress() {
        return userSocketAddress;
    }

    public void setUserSocketAddress(SocketAddress userSocketAddress) {
        this.userSocketAddress = userSocketAddress;
    }
}


