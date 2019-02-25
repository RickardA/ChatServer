package com.company;

import java.net.SocketAddress;
import java.util.ArrayList;

public class UserList {
    ArrayList <User> userList = new ArrayList<>();


    public UserList(String clientName){
        User user = new User(clientName);
        userList.add(user);

    }
    public void checkUsers (String clientName , SocketAddress socketAddress){
        for (User s : userList){
            if (clientName.equals(s.getUserName()) ){
                System.out.println(socketAddress);
                NetworkServer.get().sendObjectToClient(s, socketAddress);
                ServerProgram.get().getChatRoomsName().collectChatRoomInfo();
                NetworkServer.get().sendObjectToClient(ServerProgram.get().getChatRoomsName(),socketAddress);

            }
        }
    }
}
