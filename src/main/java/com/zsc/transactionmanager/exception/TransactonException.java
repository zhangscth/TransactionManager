package com.zsc.transactionmanager.exception;

public class TransactonException extends RuntimeException{

    String message;

    public TransactonException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
