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
    private MessageList chatHistory = new MessageList();
    private transient Thread updateChannelThread;
    static final long serialVersionUID = 20;


    public ChatRoom(String name, String id) {
        this.name = name;
        this.uniqeID = id;
        usersInChatRooom = new ArrayList<>();
    }

    private void addUserToChatRoom(User user) {
        usersInChatRooom.add(user);
    }

    private void removeUserFromChatRoom(User user) {
        usersInChatRooom.remove(user);
    }

    private void checkUsersInChatRoom() {}

    public synchronized void updateMessages(Tuple srvMsg) {
            chatHistory.setMessagesList((Message)srvMsg.right);
            System.out.println("Updating chatHistory in ChatRoom");
            for (User user : ConnectedUsers.get().getConnectedUsers()) {
                System.out.println("sending it back to each user");
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
}
