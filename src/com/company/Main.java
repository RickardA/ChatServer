package com.company;

import com.company.ChatRooms.ChatRoom;
import com.company.ChatRooms.ChatRoomList;

import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        ServerProgram.get().start();
        Scanner scan = new Scanner(System.in);
        System.out.println("q");
        String Sc = scan.nextLine();
        if (Sc.equals("q")) {
            System.out.println("i want to kill myself"); //yes
            System.exit(0);
        }

    }

}

