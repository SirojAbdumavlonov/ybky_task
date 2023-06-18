package com.example.ybky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



public class NoSuchRoomWithThisIdException extends RuntimeException{
    public NoSuchRoomWithThisIdException(String message){
        super(message);
    }
}
