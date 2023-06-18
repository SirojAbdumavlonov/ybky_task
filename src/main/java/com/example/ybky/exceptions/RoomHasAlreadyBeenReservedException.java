package com.example.ybky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class RoomHasAlreadyBeenReservedException extends RuntimeException {
    public RoomHasAlreadyBeenReservedException(String error){
        super(error);
    }
}
