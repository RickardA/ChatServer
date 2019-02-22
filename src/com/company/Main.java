package com.company;

import java.util.Scanner;

import static com.company.SaveToFile.SerializedDemo;

public class Main {


    public static void main(String[] args) {
        ServerProgram.get().start();
        Scanner scan = new Scanner(System.in);
        String Sc = scan.nextLine();
        if (Sc.equals("q")) {
            SaveToFile SerializedDemo = new SaveToFile();
            SerializedDemo();
            System.out.println("i want to kill myself"); //yes
            System.exit(0);
        }

    }

}

