package com.company.MessageSendingClasses;

import java.io.Serializable;

public class LogInRequestMessage implements Serializable {
    private String name;
    static final long serialVersionUID = 390;


    public LogInRequestMessage(String name){
        this.name = name;

    }

    public String getName(){
        return name;
    }

}
