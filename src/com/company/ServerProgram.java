package com.company;

import com.company.ChatRooms.ChatRooms;

import java.net.SocketAddress;

public class ServerProgram {

    private SocketAddress lastIncomingMessageAdress;
    private Thread myListeningThread;
    private ChatRooms chatRooms;

    public ServerProgram() {
    }

    public void start() {
        NetworkServer.get();
        chatRooms = new ChatRooms();
        myListeningThread = new Thread(() -> {
            while (true) {
                chatRooms.sendChatRoomsToClient();

                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    break;
                }
            }
        });
        myListeningThread.start();

    }
}
