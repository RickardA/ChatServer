package com.company;

import com.company.ChatRooms.ChatRoomList;
import com.company.MessageSendingClasses.ChatRoomIDMessage;
import com.company.MessageSendingClasses.HeartbeatMessage;
import com.company.User.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ConnectedUsers implements Runnable {
    private Map<String, User> connectedUsers;
    private static Map<String, String> receivedHeartbeats;

    public ConnectedUsers() {
        connectedUsers = new HashMap<>();
        receivedHeartbeats = new HashMap<>();
    }

    public void addConnectedUser(User user) {
        connectedUsers.put(user.getUserID(), user);
    }

    public void connectUserToChatRoom(ChatRoomIDMessage addToChatRoomRequest) {
        User connectedUser = connectedUsers.get(addToChatRoomRequest.getUser().getUserID());
        if (connectedUser.getChannelID() != null) {
            ChatRoomList.get().getChatRooms()
                    .get(connectedUser.getChannelID())
                    .getUsersOnlineList()
                    .removeUserFromChatRoom(connectedUser);

            ChatRoomList.get().getChatRooms()
                    .get(addToChatRoomRequest.getChatRoomID())
                    .getUsersOnlineList()
                    .addUserToChatRoom(addToChatRoomRequest.getUser().getUserID(), addToChatRoomRequest.getUser());

            connectedUser.setChannelID(addToChatRoomRequest.getChatRoomID());
        }

    }

    public Map<String, User> getConnectedUsers() {
        return connectedUsers;
    }


    public static void updateHeartbeatList(HeartbeatMessage heartbeatMessage) {
        if (!receivedHeartbeats.containsKey(heartbeatMessage.getUserID())) {
            receivedHeartbeats.put(heartbeatMessage.getUserID(), heartbeatMessage.getChannelID());
        }
    }

    private void sendHeartBeat() throws InterruptedException {
        do {
            receivedHeartbeats.clear();
            for (User user : connectedUsers.values()) {
                NetworkServer.get()
                        .sendObjectToClient(new HeartbeatMessage(
                                        user.getUserID(),
                                        user.getChannelID()),
                                user.getUserSocketAddress());
            }
            Thread.sleep(100);
            checkForDisconnect();
        } while (true);
    }

    private void checkForDisconnect() {
        Iterator<String> iterator = connectedUsers.keySet().iterator();
        while (iterator.hasNext()) {
            String userID = iterator.next();
            if (!receivedHeartbeats.containsKey(userID)) {
                System.out.println(connectedUsers.get(userID).getChannelID());
                if (connectedUsers.get(userID).getChannelID() != null) {
                    removeUserFromChatRoom(userID);
                }
                iterator.remove();
                System.out.println("User disconnected");
            }
        }
    }

    private void removeUserFromChatRoom(String userID) {
        ChatRoomList.get().getChatRooms()
                .get(connectedUsers.get(userID).getChannelID())
                .getUsersOnlineList()
                .removeUserFromChatRoom(connectedUsers.get(userID));
    }

    @Override
    public void run() {
        try {
            sendHeartBeat();
        } catch (InterruptedException e) {

        }
    }
}
