package com.company.User;

import com.company.NetworkServer;
import com.company.ServerProgram;
import com.company.User.User;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserList {
    Map<String,User> userList = new HashMap<>();


    public UserList(){
    }

    public void tryAddUser(String clientName){
        if(userList.size() != 0) {
            for (User userToCheck : userList.values()) {
                if (clientName.equals(userToCheck.getUserName())) {
                    System.out.println("user xists");
                    break;
                } else {
                    System.out.println("User does not exist");
                    createUser(clientName);
                }
            }
        }else{
            System.out.println("creating user");
            createUser(clientName);
        }
    }

    private void createUser(String clientName){
        User user = new User(clientName);
        userList.put(user.getUserID(), user);
    }

    public User checkUsers (String clientName , SocketAddress socketAddress){
        for (User s : userList.values()){
            if (clientName.equals(s.getUserName()) ){
                NetworkServer.get().sendObjectToClient(s, socketAddress);
                ServerProgram.get().getChatRoomsName().collectChatRoomInfo();
                NetworkServer.get().sendObjectToClient(ServerProgram.get().getChatRoomsName(),socketAddress);
                return s;
            }
        }
        return null;
    }
}