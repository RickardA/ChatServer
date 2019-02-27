package com.company;

import java.util.Scanner;

import static com.company.SaveToFile.SerializeToFile;

public class Main {

    public static void main(String[] args) {
        ServerProgram serverProgram = new ServerProgram();
        Scanner scan = new Scanner(System.in);
        String terminateServer;

        serverProgram.start();
        terminateServer = scan.nextLine();
        if (terminateServer.equals("q")) {
            SerializeToFile();
            System.out.println("Exiting server");
            System.exit(0);
        }

    }

}

