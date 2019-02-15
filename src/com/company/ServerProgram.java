package com.company;

import com.company.ChatRooms.ChatRoomList;

import java.net.SocketAddress;

public class ServerProgram {

    private SocketAddress lastIncomingMessageAdress;
    private Thread myListeningThread;
    private static ServerProgram _singleton = new ServerProgram();

    public ServerProgram() {
    }

    public void start() {
        NetworkServer.get();
        ChatRoomList.get();
        ChatRoomList.get().createChatRoom("General");
    }

    public void checkIncommingPackage(){
        var srvMsg = NetworkServer.get().pollMessage();
        if (srvMsg != null) {
            if(srvMsg.right instanceof Message){
                System.out.println("Message object revieved from client in check incommingPackage " + ((Message) srvMsg.right).getMessage());
                ChatRoomList.get().getChatRooms().get(0).updateMessages(srvMsg);
            }
            else if(srvMsg.right instanceof User){
                System.out.println("User " + ((User) srvMsg.right).getUserName() + " Connected! ");
                ConnectedUsers.get().addConnectedUser((User)srvMsg.right);
              /*  for (User user:ConnectedUsers.get().getConnectedUsers()) {
                    System.out.println(user.getUserName());
                }*/
                sendChatRoomsToClient(srvMsg.left);

            }
        }
    }

    public void sendChatRoomsToClient(SocketAddress sendingClientsAdress) {
        NetworkServer.get().sendObjectToClient( ChatRoomList.get(), sendingClientsAdress);
    }

    public static ServerProgram get(){
        return _singleton;
    }
}
