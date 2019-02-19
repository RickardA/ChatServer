package com.company;

import com.company.ChatRooms.ChatRoom;
import com.company.ChatRooms.ChatRoomList;

public class Main {


    public static void main(String[] args) {
        ReadFromFile readFileHistory = new ReadFromFile();
        ServerProgram.get().start();
        SaveToFile saveFileHistory = new SaveToFile(ChatRoomList.get().getChatRooms().get(0).getChatHistory());

    }
}
