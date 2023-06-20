package com.example.ybky.exceptions;

public class NoSuchRoomWithThisIdException extends RuntimeException{
    public NoSuchRoomWithThisIdException(String message){
        super(message);
    }
}
