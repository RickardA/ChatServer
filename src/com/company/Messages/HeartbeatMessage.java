package com.company.Messages;

import java.io.Serializable;

public class HeartbeatMessage implements Serializable {
    private String userID;
    private String channelID;

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
