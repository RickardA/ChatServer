package com.company;

import com.company.ChatRooms.ChatRoomList;
import com.company.MessageSendingClasses.HeartbeatMessage;
import com.company.User.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectedUsers implements Runnable {
    private Map<String, User> connectedUsers;
    private static Map<String, String> recievedHeartbeats;
    private final ReentrantLock lock = new ReentrantLock();

    public ConnectedUsers() {
        connectedUsers = new HashMap<>();
        recievedHeartbeats = new HashMap<>();
    }

    public void addConnectedUser(User user) {
        connectedUsers.put(user.getUserID(), user);
    }

    public Map<String, User> getConnectedUsers() {
        return connectedUsers;
    }


    public static void updateHeartbeatList(HeartbeatMessage heartbeatMessage) {
        if (!recievedHeartbeats.containsKey(heartbeatMessage.getUserID())) {
            recievedHeartbeats.put(heartbeatMessage.getUserID(), heartbeatMessage.getChannelID());
            ;
        }
    }

    private void sendHeartBeat() throws InterruptedException {
        do {
            recievedHeartbeats.clear();
            for (User user : connectedUsers.values()) {
                NetworkServer.get().sendObjectToClient(new HeartbeatMessage(user.getUserID(), user.getChannelID()), user.getUserSocketAddress());
            }
            Thread.sleep(100);
            checkForDisconnect();
        } while (true);
    }

    private void checkForDisconnect() {
        Iterator<String> iterator = connectedUsers.keySet().iterator();
        while (iterator.hasNext()) {
            String test = iterator.next();
            if (!recievedHeartbeats.containsKey(test)) {
                if (connectedUsers.get(test).getChannelID() != null)
                    ChatRoomList.get().getChatRooms().get(connectedUsers.get(test).getChannelID())
                            .getUsersOnlineList().removeUserFromChatRoom(connectedUsers.get(test));
                iterator.remove();
                System.out.println("User disconnected");
            }
        }
    }

    @Override
    public void run() {
        try {
            sendHeartBeat();
        } catch (InterruptedException e) {

        }
    }
}
