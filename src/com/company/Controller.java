package com.company;

import java.net.SocketAddress;

public class Controller {

    private SocketAddress lastIncomingMessageAdress;
    private Thread myListeningThread;

    public Controller(){
    }

    public void start(){
        NetworkServer.get();
        myListeningThread = new Thread(()->{
            while(true) {
                checkServerIncomingMessages();

                // Without this 1 CPU core will constantly be at 100%
                try { Thread.sleep(1); }
                catch (Exception e) { break; }
            }
        });
        myListeningThread.start();
    }

    private void checkServerIncomingMessages(){
        var srvMsg = NetworkServer.get().pollMessage();
        if (srvMsg != null) {
            lastIncomingMessageAdress = srvMsg.left;
            //System.out.println(lastIncomingMessage);
            NetworkServer.get().sendObjectToClient(srvMsg.right,srvMsg.left);
        }
    }

}
