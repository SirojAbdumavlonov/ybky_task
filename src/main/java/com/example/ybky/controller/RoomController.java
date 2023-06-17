package com.example.ybky.controller;

import com.example.ybky.entity.Reserving;
import com.example.ybky.exceptions.RoomHasAlreadyBeenReservedException;
import com.example.ybky.service.ReservingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/rooms")
public class RoomController {


    @Autowired
    private final ReservingService reservingService;

    public RoomController(ReservingService reservingService) {
        this.reservingService = reservingService;
    }

    @PostMapping("/{id}/book")
    public ResponseEntity<?> bookingRoom(@PathVariable int id,
                                      @RequestBody Reserving reserving){
        List<Date> allStartDatesOfRoom =
                reservingService.findAllStartDatesOfRoom(id);
        List<Date> allEndDatesOfRoom =
                reservingService.findAllEndDatesOfRoom(id);

        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(id).toUri();

        if(allEndDatesOfRoom == null || allStartDatesOfRoom == null){
            reservingService.saveRoomWithResident(reserving.getStart(),
                    reserving.getEnd(), reserving.getResident().getName(), id);
            return ResponseEntity.created(location)
                    .body("xona muvaffaqiyatli band qilindi");
        }

        for(int i = 0; i < allEndDatesOfRoom.size(); i++){
            if( ( reserving.getStartLikeDate().after(allStartDatesOfRoom.get(i))
                        &&
                    reserving.getStartLikeDate().before(allEndDatesOfRoom.get(i))
                )
                        ||
                (reserving.getEndLikeDate().after(allStartDatesOfRoom.get(i))
                        &&
                    reserving.getEndLikeDate().before(allEndDatesOfRoom.get(i))
                )
                        ||
                (allStartDatesOfRoom.get(i).after(reserving.getStartLikeDate())
                        &&
                allEndDatesOfRoom.get(i).before(reserving.getEndLikeDate())
                )
            )

                throw new RoomHasAlreadyBeenReservedException();
        }
        reservingService.saveRoomWithResident(reserving.getStart(),
                reserving.getEnd(), reserving.getResident().getName(), id);
        return ResponseEntity.created(location)
                .body("xona muvaffaqiyatli band qilindi");





    }


}
