package com.backend;

public class MessagePacket {
    public boolean isSuccess;
    public String message;

    public MessagePacket(boolean isSuccess, String message){
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
