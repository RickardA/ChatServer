package com.company;

import com.company.ChatRooms.ChatRoomList;
import com.company.Message.Message;
import com.company.MessageSendingClasses.HeartbeatMessage;
import com.company.MessageSendingClasses.LogInRequestMessage;
import com.company.MessageSendingClasses.*;
import com.company.User.User;
import com.company.User.UserList;
import java.net.SocketAddress;

public class ServerProgram {

    private ChatRoomNamesMessage chatRoomNames = new ChatRoomNamesMessage();
    private ConnectedUsers connectedUsers = new ConnectedUsers();
    private UserList userList = new UserList();
    private ChatRoomList chatRoomList = new ChatRoomList();
    private ReadFromFile readFromFile = new ReadFromFile();

    public ServerProgram() {
    }

    public void start() {
        readFromFile.loadChatRooms(chatRoomList);
        NetworkServer.get();
        NetworkServer.get().setMyConnectedUsers(connectedUsers);
        connectedUsers.setMyChatRoomList(chatRoomList);
        chatRoomNames.collectChatRoomInfo(chatRoomList);
        new Thread(connectedUsers).start();
        Thread incomingMessages = new Thread(this::checkIncomingPackage);
        incomingMessages.setDaemon(true);
        incomingMessages.start();
    }

    public void checkIncomingPackage() {
        while (true) {
            var incomingMsg = NetworkServer.get().pollMessage();
            if (incomingMsg != null) {
                if (incomingMsg.object instanceof Message) {
                    chatRoomList.getChatRooms().get(((Message) incomingMsg.object).getChannelID()).updateMessages(incomingMsg);
                } else if (incomingMsg.object instanceof ChosenChatRoomMessage) {
                    connectedUsers.connectUserToChatRoom((ChosenChatRoomMessage) incomingMsg.object);
                    NetworkServer.get().sendObjectToClient(chatRoomList.getChatRooms()
                            .get(((ChosenChatRoomMessage) incomingMsg.object).getChatRoomID()), incomingMsg.senderSocketAddress);
                } else if (incomingMsg.object instanceof LogInRequestMessage) {
                    userList.tryAddUser(((LogInRequestMessage) incomingMsg.object).getName());
                    User userToConnect = userList.validateUser(((LogInRequestMessage) incomingMsg.object)
                            .getName(), incomingMsg.senderSocketAddress);
                    if (userToConnect != null) {
                        sendValidatedUserToClient(userToConnect, incomingMsg.senderSocketAddress);
                    }
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void sendValidatedUserToClient(User user, SocketAddress socketAddress) {
        user.setUserSocketAddress(socketAddress);
        connectedUsers.addConnectedUser(user);
        connectedUsers.updateHeartbeatList(new HeartbeatMessage(user.getUserID(), user.getChannelID()));
        NetworkServer.get().sendObjectToClient(user, socketAddress);
        NetworkServer.get().sendObjectToClient(chatRoomNames, socketAddress);
    }

    public ChatRoomList getChatRoomList() {
        return chatRoomList;
    }
}