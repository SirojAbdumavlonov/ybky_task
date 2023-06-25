package com.example.ybky.controller;

import com.example.ybky.entity.Reserving;
import com.example.ybky.entity.Room;
import com.example.ybky.exceptions.NoSuchRoomWithThisIdException;
import com.example.ybky.exceptions.RoomHasAlreadyBeenReservedException;
import com.example.ybky.payload.AllRoomsResponse;
import com.example.ybky.payload.ApiResponse;
import com.example.ybky.payload.AvailableTimesResponse;
import com.example.ybky.service.ReservingService;
import com.example.ybky.service.RoomService;
import com.example.ybky.tools.Converter;
import com.example.ybky.utils.DateOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/rooms")
public class RoomController {

    @Autowired
    private final ReservingService reservingService;

    @Autowired
    private final RoomService roomService;

    List<AvailableTimesResponse> availableTime;

    public RoomController(ReservingService reservingService, RoomService roomService) {
        this.reservingService = reservingService;
        this.roomService = roomService;
    }

    @GetMapping()
    public ResponseEntity<AllRoomsResponse> getAllDataOfRooms(@RequestParam(name = "search", required = false) String roomName,
                                               @RequestParam(name = "type", required = false) String roomType,
                                               @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                               @RequestParam(name = "page_size", required = false, defaultValue = "10") int page_size
                                                ){
        List<Room> rooms =
                roomService.findAllRoomsByParams(
                        roomName, roomType, page, page_size
                );
        return ResponseEntity.ok(new AllRoomsResponse(
                page, rooms.size(), page_size, rooms
        ));
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
                ((Converter.convertStringToDate(reserving.getStart()) == allStartDatesOfRoom.get(i))
                            &&
                (Converter.convertStringToDate(reserving.getEnd()) == allEndDatesOfRoom.get(i)))
                        ||
                (Converter.convertStringToDate(reserving.getStart()).after(allStartDatesOfRoom.get(i))//checking start Date if it is after start
                        &&
                (Converter.convertStringToDate(reserving.getStart())).before(allEndDatesOfRoom.get(i)))//checking start Date if it is before end
                        ||//checking start if it is between of one booking time(start and end)
                (Converter.convertStringToDate(reserving.getEnd())).after(allStartDatesOfRoom.get(i))//checking end Date if it is after start
                        &&
                (Converter.convertStringToDate(reserving.getEnd())).before(allEndDatesOfRoom.get(i))//checking end Date if it is before end
                        ||//checking end if it is between of one booking time(start and end)
                (allStartDatesOfRoom.get(i).after(Converter.convertStringToDate(reserving.getStart())))
                        &&//checking if start and end inside of new booking time(start and end)
                (allEndDatesOfRoom.get(i).before(Converter.convertStringToDate(reserving.getEnd())))
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
    @GetMapping("/{id}/availability")
    public ResponseEntity<List<AvailableTimesResponse>> showAvailableTimeOfRoom(@PathVariable int id,
                                                                @RequestParam(name = "date",required = false) String date){
        if(date == null){
            date = DateOptions.getCurrentDate();
        }
        roomService.findRoomById(id).orElseThrow(() ->
                new NoSuchRoomWithThisIdException("bunday son bilan hona yoq!")
        );

        List<String> allStartsOfRoom = reservingService.allStartsByIdAndDate(id, date);
        List<String> allEndsOfRoom = reservingService.allEndsByIdAndDate(id, date);

        availableTime = new ArrayList<>(allStartsOfRoom.size());

        allStartsOfRoom.add(DateOptions.getDatePlus2359(date));//adding current date + 23:59:59 to List
        allEndsOfRoom.add(0, DateOptions.getDatePlus0000(date));//adding current date + 00:00:00 to List

        for (int i = 0; i < allEndsOfRoom.size(); i++){
            availableTime.add(i, new AvailableTimesResponse(allEndsOfRoom.get(i),
                    allStartsOfRoom.get(i)));
        }
        return ResponseEntity.ok(availableTime);
    }
}
