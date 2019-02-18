package com.company;

import com.company.ChatRooms.ChatRoom;
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
        Thread incommingMessages = new Thread(this::checkIncommingPackage);
        incommingMessages.setDaemon(true);
        incommingMessages.start();
    }

    public void checkIncommingPackage(){
        while (true) {
            var srvMsg = NetworkServer.get().pollMessage();
            if (srvMsg != null) {
                if (srvMsg.right instanceof Message) {
                    System.out.println("Message object revieved from client in check incommingPackage " +
                            ((Message) srvMsg.right).getMessage());
                    System.out.println("Message object channel ID: " + ((Message) srvMsg.right).getChannelID());
                    ChatRoomList.get().getChatRooms().get(0).updateMessages(srvMsg);
                } else if (srvMsg.right instanceof User) {
                    System.out.println("User " + ((User) srvMsg.right).getUserName() + " Connected! ");
                    ConnectedUsers.get().addConnectedUser((User) srvMsg.right);
              /*  for (User user:ConnectedUsers.get().getConnectedUsers()) {
                    System.out.println(user.getUserName());
                }*/
                    sendChatRoomsToClient(srvMsg.left);

                } else if (srvMsg.right instanceof String) {
                    System.out.println("The user "
                            // the line below doesn't work.. it should? look at line 42-43 in ChatRoom
                            //+ ChatRoomList.get().getChatRooms().get(0).getUsersInChatRooom().get(0).getUserSocketAddress()
                            +  ((String) srvMsg.right) + " joined chat room:" + " and the message/channel " +
                            "ID sent from user was: " + (((String) srvMsg.right)));
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
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
