package com.company.MessageSendingClasses;

import java.io.Serializable;

public class LogInRequestMessage implements Serializable {
    private String name;


    public LogInRequestMessage(String name){
        this.name = name;

    }

    public String getName(){
        return name;
    }

}
