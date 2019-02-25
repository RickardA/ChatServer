package com.company.ChatRooms;

import com.company.*;
import com.company.Message.Message;
import com.company.Message.MessageList;
import com.company.User.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ChatRoom implements Serializable {
    private String uniqeID;
    private String name;
    private UsersOnlineList usersOnlineList;
    private MessageList chatHistory = new MessageList();
    private transient Thread updateChannelThread;
    static final long serialVersionUID = 20;

    public ChatRoom(String name, String id) {
        this.name = name;
        this.uniqeID = id;
        this.usersOnlineList = new UsersOnlineList();
    }

    public synchronized void updateMessages(Tuple srvMsg) {
            chatHistory.setMessagesList((Message)srvMsg.right);
            for (User user : usersOnlineList.getUsersOnlineList().values()) {
                NetworkServer.get().sendObjectToClient(chatHistory.getMessagesList().get(chatHistory.getMessagesList().size() -1), user.getUserSocketAddress());
            }
    }

    public String getUniqeID() {
        return uniqeID;
    }
    public String getName() {
        return name;
    }

    public UsersOnlineList getUsersOnlineList() {
        return usersOnlineList;
    }
}
