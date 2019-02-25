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
    private Map<String, User> usersInChatRooom;
    private MessageList chatHistory = new MessageList();
    private transient Thread updateChannelThread;
    static final long serialVersionUID = 20;


    public ChatRoom(String name, String id) {
        this.name = name;
        this.uniqeID = id;
        usersInChatRooom = new HashMap<>();
    }

    public void addUserToChatRoom(String id,User user) {
        usersInChatRooom.put(id,user);
    }

    public void removeUserFromChatRoom(User user) {
        System.out.println(usersInChatRooom.size());
        usersInChatRooom.remove(user.getUserID());
        System.out.println(usersInChatRooom.size());
    }

    private void checkUsersInChatRoom() {}

    public synchronized void updateMessages(Tuple srvMsg) {
            chatHistory.setMessagesList((Message)srvMsg.right);
            for (User user : usersInChatRooom.values()) {
                NetworkServer.get().sendObjectToClient(chatHistory.getMessagesList().get(chatHistory.getMessagesList().size() -1), user.getUserSocketAddress());
            }
    }

    public String getUniqeID() {
        return uniqeID;
    }

    public void setUniqeID(String uniqeID) {
        this.uniqeID = uniqeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String,User> getUsersInChatRooom() {
        return usersInChatRooom;
    }


}
