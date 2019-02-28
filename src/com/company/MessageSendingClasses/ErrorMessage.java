package com.company.MessageSendingClasses;

import java.io.Serializable;

public class ErrorMessage implements Serializable {
    String errorMessage;
    static final long serialVersionUID = 8877;

    public ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
