package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ServerProgram serverProgram = new ServerProgram();
        Scanner scan = new Scanner(System.in);

        serverProgram.start();
        String terminateServer = scan.nextLine();
        if (terminateServer.equals("q")) {
            SaveToFile saveToFile = new SaveToFile();
            saveToFile.SerializeToFile(serverProgram.getChatRoomList());
            System.out.println("Exiting server");
            System.exit(0);
        }

    }

}

