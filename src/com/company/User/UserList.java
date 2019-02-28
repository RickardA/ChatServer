package com.company.User;

import com.company.ConnectedUsers;
import com.company.MessageSendingClasses.ErrorMessage;
import com.company.NetworkServer;

import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserList {
    Map<String, User> userList = new ConcurrentHashMap<>();

    public UserList() {
        createUser("Rickard", "password1234");
        createUser("Nisse", "password1234");
    }

    /*public void tryAddUser(String clientName) {
        if (userList.size() != 0) {
            for (User userToCheck : userList.values()) {
                if (clientName.equals(userToCheck.getUserName())) {
                    break;
                } else {
                    createUser(clientName);
                }
            }
        } else {
            createUser(clientName);
        }
    }*/

    private void createUser(String clientName, String password) {
        User user = new User(clientName, password);
        userList.put(user.getUserID(), user);
    }

    public User validateUser(String clientName, String password, ConnectedUsers connectedUsers, SocketAddress socketAddress) {
        for (User user : userList.values()) {
            if (!clientName.toUpperCase().equals(user.getUserName().toUpperCase())) {
                NetworkServer.get().sendErrorMessageToClient("User does not exist", socketAddress);
                return null;
            }
            if (!password.equals(user.getPassword())) {
                NetworkServer.get()
                        .sendErrorMessageToClient("Wrong password", socketAddress);
                return null;
            }
            if (connectedUsers.getConnectedUsers().containsKey(user.getUserID())) {
                NetworkServer.get()
                        .sendErrorMessageToClient("User already logged in", socketAddress);
                return null;
            }
            return user;
        }
        return null;
    }
}
