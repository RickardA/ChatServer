package com.company.ChatRooms;

import com.company.*;
import com.company.Message.Message;
import com.company.Message.MessageList;
import com.company.User.User;

import java.io.Serializable;

public class ChatRoom implements Serializable {
    private String uniqeID;
    private String name;
    private UsersOnlineList usersOnlineList;
    private MessageList chatHistory = new MessageList();
    static final long serialVersionUID = 20;

    public ChatRoom(String name, String id) {
        this.name = name;
        this.uniqeID = id;
        this.usersOnlineList = new UsersOnlineList();
        chatHistory.createWelcomeMessage(name);
    }

    //Updates chatrooms message history and sends new message to users connected to chatRoom
    public synchronized void updateMessages(Tuple srvMsg) {
        chatHistory.setMessagesList((Message) srvMsg.object);
        for (User user : usersOnlineList.getUsersOnlineList().values()) {
            NetworkServer.get()
                    .sendObjectToClient(chatHistory.getMessagesList()
                            .get(chatHistory.getMessagesList().size() - 1), user.getUserSocketAddress());
        }
    }

    public String getUniqeID() {
        return uniqeID;
    }

    public String getName() {
        return name;
    }

    public UsersOnlineList getUsersOnlineList() {
        return usersOnlineList;
    }
}
