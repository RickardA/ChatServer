package com.company.User;

import com.company.ConnectedUsers;
import com.company.NetworkServer;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserList {
    private Map<String, User> userList = new ConcurrentHashMap<>();

    public UserList() {
        createUser("Rickard", "password1234");
        createUser("Nisse", "password1234");
    }

    private void createUser(String clientName, String password) {
        User user = new User(clientName, password);
        userList.put(user.getUserName().toUpperCase(), user);
    }

    public User validateUser(String clientName, String password, ConnectedUsers connectedUsers, SocketAddress socketAddress) {
        clientName = clientName.toUpperCase();
        if (!userList.containsKey(clientName)){
            NetworkServer.get().sendErrorMessageToClient("User does not exist",socketAddress);
            return null;
        }
        else if (!userList.get(clientName).getPassword().equals(password)){
            NetworkServer.get().sendErrorMessageToClient("Password does not match",socketAddress);
            return null;
        }
        else if (connectedUsers.getConnectedUsers().containsKey(userList.get(clientName).getUserID())){
            NetworkServer.get().sendErrorMessageToClient("User is already connected",socketAddress);
            return null;
        }
        return userList.get(clientName);
    }
}
