package com.example.ybky.exceptions;

import com.example.ybky.payload.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {NoSuchRoomWithThisIdException.class})
    public ResponseEntity<Object> handleNotExistingRoomRequest(NoSuchRoomWithThisIdException e){
        ApiException ap = new ApiException(
                e.getMessage()
        );
        return new ResponseEntity<>(ap, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {RoomHasAlreadyBeenReservedException.class})
    public ResponseEntity<Object> handleAlreadyRegisteredRoomRequest(RoomHasAlreadyBeenReservedException e){
        ApiException ap = new ApiException(
                e.getMessage()
        );
        return new ResponseEntity<>(ap, HttpStatus.GONE);
    }



}
