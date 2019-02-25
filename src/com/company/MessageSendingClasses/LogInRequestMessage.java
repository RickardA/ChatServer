package com.company.MessageSendingClasses;

import java.io.Serializable;

public class LogInRequestMessage implements Serializable {
    private String name;
    private String password;

    public LogInRequestMessage(String name, String password){
        this.name = name;
        this.password= password;
    }

    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }


}
