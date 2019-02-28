package com.company.MessageSendingClasses;

import java.io.Serializable;

public class ErrorMessage implements Serializable {
    private String errorMessage;
    static final long serialVersionUID = 8877;

    public ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
