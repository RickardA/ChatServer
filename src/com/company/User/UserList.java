package com.company.User;

import com.company.ConnectedUsers;

import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserList {
    Map<String, User> userList = new ConcurrentHashMap<>();

    public UserList() {
        createUser("Rickard","password1234");
        createUser("Nisse","password1234");
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

    private void createUser(String clientName,String password) {
        User user = new User(clientName,password);
        userList.put(user.getUserID(), user);
    }

    public User validateUser(String clientName,String password, ConnectedUsers connectedUsers) {
        for (User user : userList.values()) {
            if (clientName.equals(user.getUserName()) && password.equals(user.getPassword())) {
                if (!connectedUsers.getConnectedUsers().containsKey(user.getUserID())){
                    return user;
                }
            }
        }
        return null;
    }
}