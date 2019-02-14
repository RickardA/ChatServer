package com.company.ChatRooms;

import com.company.Message;
import com.company.NetworkServer;
import com.company.User;

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
        usersInChatRooom = new ArrayList<>();
        chatHistory = new ArrayList<>();

        updateChannelThread = new Thread(() -> {
            while (true) {
                updateMessages();
                try {
                    Thread.sleep(10);

                } catch (InterruptedException e) {
                    break;
                }

            }
        });
    }

    private void addUserToChatRoom(User user) {
        usersInChatRooom.add(user);
    }

    private void removeUserFromChatRoom(User user) {
        usersInChatRooom.remove(user);
    }

    private void checkUsersInChatRoom() {

    }

    private void updateMessages() {
        NetworkServer.get().pollMessage();

        var srvMsg = NetworkServer.get().pollMessage();
        if (srvMsg != null) {
            messages.add(srvMsg.right);
            for (User user : usersInChatRooom) {
                NetworkServer.get().sendObjectToClient(messages, user.getUserSocketAddress());
            }
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
