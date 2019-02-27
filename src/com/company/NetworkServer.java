package com.company;

import com.company.MessageSendingClasses.HeartbeatMessage;
import com.company.User.User;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

public class NetworkServer {
    public final int PORT = 8080;
    private final int MSG_SIZE = 1000000;

    private LinkedBlockingDeque<Tuple<SocketAddress, Object>> msgQueue = new LinkedBlockingDeque<>();

    private DatagramSocket socket;
    private static NetworkServer _singleton = new NetworkServer();

    private NetworkServer() {
        try {
            socket = new DatagramSocket(PORT);
            socket.setSoTimeout(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread t = new Thread(this::loop);
        t.setDaemon(true);
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

        //Bug fixed for Mac. Force server to send back object to the right socketaddress (Rami)
        if (clientSocketAddress.toString().startsWith(("0.0.0.0/0.0.0.0"))) {
            request.setSocketAddress(new InetSocketAddress("127.0.0.1", request.getPort()));
        }

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

            Object msg = deserializeRequest(clientRequest.getData());
            if(msg instanceof HeartbeatMessage){
                ConnectedUsers.updateHeartbeatList((HeartbeatMessage)msg);
            }else {
                msgQueue.addLast(new Tuple(clientRequest.getSocketAddress(), msg));
            }
        }
    }

    private boolean receiveMsgFromAnyClient(DatagramPacket clientRequest) {
        try {
            socket.receive(clientRequest);
            return true;
        } catch (SocketTimeoutException e) { 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private Object deserializeRequest(byte[] clientRequest) {
        try {
            try (ByteArrayInputStream bin = new ByteArrayInputStream(clientRequest)) {
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
