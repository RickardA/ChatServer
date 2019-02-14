package com.company;

import java.util.ArrayList;

public class ConnectedUsers {

    private ArrayList<User> connectedUsers = new ArrayList<>();
    private static ConnectedUsers _singelton = new ConnectedUsers();

    public ConnectedUsers(){}

    public static ConnectedUsers get(){
        return _singelton;
    }

    public ArrayList<User> getConnectedUsers() {
        return connectedUsers;
    }

    public void addConnectedUser(User user) {
        this.connectedUsers.add(user);
    }
}
