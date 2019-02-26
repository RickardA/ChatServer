package com.company;

import com.company.ChatRooms.ChatRoomList;
import com.company.Message.Message;
import com.company.MessageSendingClasses.HeartbeatMessage;
import com.company.MessageSendingClasses.LogInRequestMessage;
import com.company.MessageSendingClasses.*;
import com.company.User.User;
import com.company.User.UserList;


import java.net.SocketAddress;

import static com.company.ReadFromFile.LoadChatRooms;

public class ServerProgram {

    private SocketAddress lastIncomingMessageAdress;
    private Thread myListeningThread;
    private static ServerProgram _singleton = new ServerProgram();
    private ChatRoomListMessage chatRoomOptions = new ChatRoomListMessage();
    private ConnectedUsers connectedUsers;
    private UserList userList;

    public ServerProgram() {
    }

    public void start() {
        NetworkServer.get();
        ChatRoomList.get();
        LoadChatRooms();
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
                    connectedUsers.addConnectedUser((User) srvMsg.right);
                    ConnectedUsers.updateHeartbeatList(new HeartbeatMessage(((User) srvMsg.right).getUserID(), ((User) srvMsg.right).getChannelID()));
                    chatRoomsListName(srvMsg.left);
                } else if (srvMsg.right instanceof ChatRoomIDMessage) {
                    ChatRoomList.get().getChatRooms().get(((ChatRoomIDMessage) srvMsg.right).getChatRoomID())
                            .getUsersOnlineList().addUserToChatRoom(((ChatRoomIDMessage) srvMsg.right).getUser()
                            .getUserID(), ((ChatRoomIDMessage) srvMsg.right).getUser());
                    NetworkServer.get().sendObjectToClient(ChatRoomList.get().getChatRooms()
                            .get(((ChatRoomIDMessage) srvMsg.right).getChatRoomID()), srvMsg.left);
                } else if (srvMsg.right instanceof LogInRequestMessage) {
                    userList = new UserList(((LogInRequestMessage) srvMsg.right).getName());
                    System.out.println("Recivied user: " + ((LogInRequestMessage) srvMsg.right).getName());
                    System.out.println(srvMsg.left);
                    userList.checkUsers(((LogInRequestMessage) srvMsg.right).getName(), srvMsg.left);
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

    public ChatRoomListMessage getChatRoomsName() {
        return chatRoomOptions;
    }

    public static ServerProgram get() {
        return _singleton;
    }
}
