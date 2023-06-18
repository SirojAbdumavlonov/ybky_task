package com.example.ybky.controller;

import com.example.ybky.entity.Reserving;
import com.example.ybky.entity.Room;
import com.example.ybky.exceptions.NoSuchRoomWithThisIdException;
import com.example.ybky.exceptions.RoomHasAlreadyBeenReservedException;
import com.example.ybky.payload.ApiResponse;
import com.example.ybky.repository.RoomRepository;
import com.example.ybky.service.ReservingService;
import com.example.ybky.service.RoomService;
import com.example.ybky.tools.StringToDateConverter;
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

    @Autowired
    private final RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;



    public RoomController(ReservingService reservingService, RoomService roomService) {
        this.reservingService = reservingService;
        this.roomService = roomService;
    }

    @PostMapping("/{id}/book")
    public ResponseEntity<?> bookingRoom(@PathVariable int id,
                                      @RequestBody Reserving reserving) throws NullPointerException{

        roomService.findRoomById(id).orElseThrow(() ->
                new NoSuchRoomWithThisIdException("bunday son bilan hona yoq!")
        );
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(id).toUri();

        if(reservingService.findAllStartDatesOfRoom(id) == null || reservingService.findAllEndDatesOfRoom(id) == null){
            reservingService.saveRoomWithResident(reserving.getStart(), reserving.getEnd(), reserving.getResident().getName(), id);
            return ResponseEntity.created(location)
                    .body(new ApiResponse("xona muvaffaqiyatli band qilindi"));
        }
        List<Date> allStartDatesOfRoom =
                reservingService.findAllStartDatesOfRoom(id);
        List<Date> allEndDatesOfRoom =
                reservingService.findAllEndDatesOfRoom(id);

        for(int i = 0; i < allEndDatesOfRoom.size(); i++){
            if(
                ((StringToDateConverter.convertStringToDate(reserving.getStart()).after(allStartDatesOfRoom.get(i))
                        &&
                (StringToDateConverter.convertStringToDate(reserving.getStart()).before(allEndDatesOfRoom.get(i))))
                        ||
                (StringToDateConverter.convertStringToDate(reserving.getEnd()).after(allStartDatesOfRoom.get(i))
                        &&
                (StringToDateConverter.convertStringToDate(reserving.getEnd()).before(allEndDatesOfRoom.get(i))))
                        ||
                (allStartDatesOfRoom.get(i).after(StringToDateConverter.convertStringToDate(reserving.getStart()))
                        &&
                (allEndDatesOfRoom.get(i).before(StringToDateConverter.convertStringToDate(reserving.getEnd())))))
            )
                throw new RoomHasAlreadyBeenReservedException("uzr, siz tanlagan vaqtda xona band");
        }
        reservingService.saveRoomWithResident(reserving.getStart(),
                reserving.getEnd(), reserving.getResident().getName(), id);
        return ResponseEntity.created(location).body(new ApiResponse("xona muvaffaqiyatli band qilindi"));
    }

    @GetMapping("/{id}")
    public Room showDetailsOfRoom(@PathVariable int id){
        return roomService.findRoomById(id).orElseThrow(() ->
            new NoSuchRoomWithThisIdException("topilmadi")
        );
    }
    @PostMapping("/addRoom")
    public Room addRoom(@RequestBody Room room){
        return roomRepository.save(room);
    }


}
