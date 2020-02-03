package com.backend;

public class Message {
    private boolean isSuccess;
    private String message;

    public Message(boolean isSuccess, String message){
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
