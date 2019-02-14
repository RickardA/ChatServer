package com.company;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

public class NetworkServer {
    public final int PORT = 9001;
    private final int MSG_SIZE = 1024;

    // In the Server we store both "WHO sent the msg and WHAT was the msg"
    private LinkedBlockingDeque<Tuple<SocketAddress, Object>> msgQueue = new LinkedBlockingDeque<>();

    private DatagramSocket socket;
    private static NetworkServer _singleton = new NetworkServer();
    public ArrayList<User> usersConnected = new ArrayList<>();

    private NetworkServer() {
        try {
            socket = new DatagramSocket(PORT);
            socket.setSoTimeout(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread t = new Thread(this::loop);
        //Why!!!!!!! t.setDaemon(true);
        t.start();
    }

    public static NetworkServer get() {
        return _singleton;
    }

    public Tuple<SocketAddress, Object> pollMessage() {
        return msgQueue.pollFirst();
    }


    public void sendObjectToClient(Object object, SocketAddress clientSocketAddress) {
        ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(byteArrayStream)) {
            out.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        DatagramPacket request = new DatagramPacket(byteArrayStream.toByteArray(), byteArrayStream.size(), clientSocketAddress);
        try {
            socket.send(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loop() {
        System.out.println("Server is up and running");
        while (true) {
            DatagramPacket clientRequest = new DatagramPacket(new byte[MSG_SIZE], MSG_SIZE);

            if (!receiveMsgFromAnyClient(clientRequest)) {
                continue;
            }

            Object msg = deserializeRequest(clientRequest);
            if (msg instanceof User) {
                usersConnected.add((User) msg);
            } else {
                Message message = (Message) msg;
                System.out.println(message.getMessage());
                msgQueue.addLast(new Tuple(clientRequest.getSocketAddress(), msg));
            }
        }
    }

    private boolean receiveMsgFromAnyClient(DatagramPacket clientRequest) {
        try {
            socket.receive(clientRequest);
            return true;
        } catch (SocketTimeoutException e) { // Ignore timeout
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private Object deserializeRequest(DatagramPacket clientRequest) {
        try {
            try (ByteArrayInputStream bin = new ByteArrayInputStream(clientRequest.getData())) {
                try (ObjectInputStream ois = new ObjectInputStream(bin)) {
                    return ois.readObject();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
