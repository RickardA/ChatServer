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

    private ChatRoomListMessage chatRoomOptions = new ChatRoomListMessage();
    private ConnectedUsers connectedUsers;
    private UserList userList = new UserList();

    public ServerProgram() {
    }

    public void start() {
        NetworkServer.get();
        ChatRoomList.get();
        //Calls for LoadChatRooms to get saved chatrooms from file
        LoadChatRooms();
        new Thread(connectedUsers = new ConnectedUsers()).start();
        Thread incomingMessages = new Thread(this::checkIncomingPackage);
        incomingMessages.setDaemon(true);
        incomingMessages.start();
    }

    public void checkIncomingPackage() {
        while (true) {
            var srvMsg = NetworkServer.get().pollMessage();
            if (srvMsg != null) {
                if (srvMsg.right instanceof Message) {
                    ChatRoomList.get().getChatRooms().get(((Message) srvMsg.right).getChannelID()).updateMessages(srvMsg);
                } else if (srvMsg.right instanceof ChosenChatRoomMessage) {
                    System.out.println("Joining chatRoom");
                    connectedUsers.connectUserToChatRoom((ChosenChatRoomMessage) srvMsg.right);
                    NetworkServer.get().sendObjectToClient(ChatRoomList.get().getChatRooms()
                            .get(((ChosenChatRoomMessage) srvMsg.right).getChatRoomID()), srvMsg.left);
                } else if (srvMsg.right instanceof LogInRequestMessage) {
                    System.out.println("login request");
                    ///////////////////////////Detta skall samlas på ett snyggare sätt//////////////////////////////////////////////////
                    userList.tryAddUser(((LogInRequestMessage) srvMsg.right).getName());
                    User userToConnect = userList.checkUsers(((LogInRequestMessage) srvMsg.right).getName(), srvMsg.left);
                    connectedUsers.addConnectedUser(userToConnect);
                    ConnectedUsers.updateHeartbeatList(new HeartbeatMessage(userToConnect.getUserID(), userToConnect.getChannelID()));
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////
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

}