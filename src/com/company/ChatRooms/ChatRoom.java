package com.company.ChatRooms;

import com.company.*;

import java.beans.Transient;
import java.io.Serializable;
import java.net.SocketAddress;
import java.util.ArrayList;

public class ChatRoom implements Serializable {
    private String uniqeID;
    private String name;
    private ArrayList<User> usersInChatRooom;
    private ArrayList<User> chatHistory;
    private transient Thread updateChannelThread;
    private ArrayList<Object> messages;
    static final long serialVersionUID = 20;


    public ChatRoom(String name, String id) {
        this.name = name;
        this.uniqeID = id;
        messages = new ArrayList<>();
        usersInChatRooom = new ArrayList<>();
        chatHistory = new ArrayList<>();
    }

    private void addUserToChatRoom(User user) {
        usersInChatRooom.add(user);
    }

    private void removeUserFromChatRoom(User user) {
        usersInChatRooom.remove(user);
    }

    private void checkUsersInChatRoom() {

    }

    public void updateMessages(Tuple srvMsg) {
            messages.add(srvMsg.right);
            for (User user : ConnectedUsers.get().getConnectedUsers()) {
                NetworkServer.get().sendObjectToClient(messages, user.getUserSocketAddress());
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
}
