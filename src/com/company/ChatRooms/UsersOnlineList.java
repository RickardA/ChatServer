package com.company.ChatRooms;

import com.company.User.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UsersOnlineList implements Serializable {
    private Map<String, User> usersOnlineList;
    static final long serialVersionUID = 260;

    public UsersOnlineList() {
        usersOnlineList = new HashMap<>();
    }

    public void addUserToChatRoom(String id,User user) {
        usersOnlineList.put(id,user);
    }

    public void removeUserFromChatRoom(User user) {
        usersOnlineList.remove(user.getUserID());
    }

    public Map<String, User> getUsersOnlineList() {
        return usersOnlineList;
    }

    public void resetUserOnlineList() {
        usersOnlineList.clear();
    }
}
