package com.company;

import com.company.ChatRooms.ChatRoom;
import com.company.ChatRooms.ChatRooms;

import java.net.SocketAddress;

public class ServerProgram {

    private SocketAddress lastIncomingMessageAdress;
    private Thread myListeningThread;
    private static ServerProgram _singleton = new ServerProgram();

    public ServerProgram() {
    }

    public void start() {
        NetworkServer.get();
        ChatRooms.get();
    }

    public void checkIncommingPackage(){
        var srvMsg = NetworkServer.get().pollMessage();
        if (srvMsg != null) {
            if(srvMsg.right instanceof Message){
                ChatRooms.get().getChatRoomList().get(0).updateMessages(srvMsg);
            }
            else if(srvMsg.right instanceof User){
                System.out.println("User " + ((User) srvMsg.right).getUserName() + " Connected! ");
                ConnectedUsers.get().addConnectedUser((User)srvMsg.right);
                for (User user:ConnectedUsers.get().getConnectedUsers()) {
                    System.out.println(user.getUserName());
                }
                ChatRooms.get().sendChatRoomsToClient(srvMsg.left);

            }
        }
    }

    public static ServerProgram get(){
        return _singleton;
    }
}
