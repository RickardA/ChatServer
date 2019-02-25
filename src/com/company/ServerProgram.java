package com.company;

import com.company.ChatRooms.ChatRoomList;
import com.company.Messages.HeartbeatMessage;


import java.net.SocketAddress;

public class ServerProgram {

    private SocketAddress lastIncomingMessageAdress;
    private Thread myListeningThread;
    private static ServerProgram _singleton = new ServerProgram();
    private Wrapper chatRoomOptions = new Wrapper();
    private ConnectedUsers connectedUsers;

    public ServerProgram() {
    }

    public void start() {
        NetworkServer.get();
        ChatRoomList.get();
        ChatRoomList.get().createChatRoom("General");
        ChatRoomList.get().createChatRoom("Study Room");
        new Thread(connectedUsers = new ConnectedUsers()).start();
        Thread incommingMessages = new Thread(this::checkIncommingPackage);
        incommingMessages.setDaemon(true);
        incommingMessages.start();
    }

    public void checkIncommingPackage() {
        while (true) {
            var srvMsg = NetworkServer.get().pollMessage();
            if (srvMsg != null) {
                if (srvMsg.right instanceof Message) {
                    ChatRoomList.get().getChatRooms().get(((Message) srvMsg.right).getChannelID()).updateMessages(srvMsg);
                } else if (srvMsg.right instanceof User) {
                    connectedUsers.addConnectedUser((User)srvMsg.right);
                    ConnectedUsers.updateHeartbeatList(new HeartbeatMessage(((User) srvMsg.right).getUserID(),((User) srvMsg.right).getChannelID()));
                    chatRoomsListName(srvMsg.left);
                } else if (srvMsg.right instanceof Wrapper) {
                    ChatRoomList.get().getChatRooms().get(((Wrapper) srvMsg.right).getChatRoomID()).addUserToChatRoom(((Wrapper) srvMsg.right).getUser().getUserID(),((Wrapper) srvMsg.right).getUser());
                    NetworkServer.get().sendObjectToClient(ChatRoomList.get().getChatRooms().get(((Wrapper) srvMsg.right).getChatRoomID()), srvMsg.left);
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void chatRoomsListName(SocketAddress socketAddress) {
        chatRoomOptions.collectChatRoomInfo();
        NetworkServer.get().sendObjectToClient(chatRoomOptions, socketAddress);
    }

    public static ServerProgram get() {
        return _singleton;
    }
}
