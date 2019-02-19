package com.company;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.SocketAddress;
import java.util.UUID;

public class User implements Serializable{

    static final long serialVersionUID = 12;
    private String userName;
  /*  private String userID;*/
    private String channelID;
    private SocketAddress userSocketAddress;


    public User(String name) {
        //Creates a uniqe id and sets it to userID;
        userName = name;
        //userID = UUID.randomUUID().toString();
    }

    public String getUserName() {
        return userName;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

   /* public String getUserID() {
        return userID;
    }*/
/*
    public void setUserID(String userID) {
        this.userID = userID;
    }*/

    public SocketAddress getUserSocketAddress() {
        return userSocketAddress;
    }

   /* public void setUserSocketAddress() {
        try {
            this.userSocketAddress = NetworkClient.get().socket.getLocalSocketAddress();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }*/
}
