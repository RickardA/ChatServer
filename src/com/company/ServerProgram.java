package com.company;

import com.company.ChatRooms.ChatRoom;
import com.company.ChatRooms.ChatRoomList;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServerProgram {

    private SocketAddress lastIncomingMessageAdress;
    private Thread myListeningThread;
    private static ServerProgram _singleton = new ServerProgram();
    private Wrapper chatRoomOptions = new Wrapper();

    public ServerProgram() {
    }

    public void start() {
        NetworkServer.get();
        ChatRoomList.get();
        ChatRoomList.get().createChatRoom("General");
        ChatRoomList.get().createChatRoom("Study Room");
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
                    chatRoomsListName(srvMsg.left);
              /*  for (User user:ConnectedUsers.get().getConnectedUsers()) {
                    System.out.println(user.getUserName());
                }*/


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

    public void chatRoomsListName(SocketAddress socketAddress ){
        chatRoomOptions.collectChatRoomInfo();
        System.out.println("Namn på kanaler: " + chatRoomOptions.getChatRoomOptions() );
        NetworkServer.get().sendObjectToClient(chatRoomOptions, socketAddress);
    }

    public static ServerProgram get(){
        return _singleton;
    }
}
