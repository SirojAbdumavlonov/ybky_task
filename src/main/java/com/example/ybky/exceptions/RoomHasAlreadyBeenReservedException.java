package com.example.ybky.exceptions;

public class RoomHasAlreadyBeenReservedException extends RuntimeException {
    public RoomHasAlreadyBeenReservedException(String error){
        super(error);
    }
}
