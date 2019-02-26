package com.company;

import java.util.Scanner;

import static com.company.SaveToFile.SerializeToFile;

public class Main {


    public static void main(String[] args) {
        ServerProgram.get().start();
        Scanner scan = new Scanner(System.in);
        String Sc = scan.nextLine();
        if (Sc.equals("q")) { //checks for q in console to save chatrooms to file and close the server
            SerializeToFile(); //Calls SerializeToFile to save current chatrooms to a file when you write q in he console
            System.out.println("Exiting server"); //Not needed
            System.exit(0); //close down the server
        }

    }

}

