package com.example.ybky.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GONE)
public class RoomHasAlreadyBeenReservedException extends RuntimeException {
    public RoomHasAlreadyBeenReservedException(){
        super("uzr, siz tanlagan vaqtda hona band");
    }
}
