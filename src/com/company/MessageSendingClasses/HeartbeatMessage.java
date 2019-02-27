package com.company.MessageSendingClasses;

import java.io.Serializable;

public class HeartbeatMessage implements Serializable {
    private String userID;
    private String channelID;
    static final long serialVersionUID = 990;

    public HeartbeatMessage(String userID, String channelID) {
        this.userID = userID;
        this.channelID = channelID;
    }

    public String getUserID() {
        return userID;
    }

    public String getChannelID() {
        return channelID;
    }
}
