package com.example.ybky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchRoomWithThisIdException extends RuntimeException{
    public NoSuchRoomWithThisIdException(int id){
        super(String.format("Room with %d id does not exist",id));
    }
}
