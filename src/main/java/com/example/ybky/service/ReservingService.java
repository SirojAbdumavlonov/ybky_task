package com.example.ybky.service;

import com.example.ybky.entity.Reserving;
import com.example.ybky.entity.Resident;
import com.example.ybky.entity.Room;
import com.example.ybky.repository.ReservingRepository;
import com.example.ybky.tools.StringToDateConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservingService {

    @Autowired
    private static ReservingRepository reservingRepository;


    @Transactional
    public void saveRoomWithResident(String start, String end, String residentName, int roomId){
        Resident resident =
                Resident.builder()
                        .name(residentName)
                        .build();
        Room room =
                Room.builder()
                        .id(roomId)
                        .build();
        Reserving reserving =
                    Reserving.builder()
                            .resident(resident)
                            .start(start)
                            .end(end)
                            .room(room)
                            .build();
        reservingRepository.save(reserving);
    }
    public List<Date> findAllStartDatesOfRoom(int id){
        return StringToDateConverter.convertArrayOfStringsToArrayOfDates(
                reservingRepository.findAllStartsOfThisRoom(id));
    }
    public List<Date> findAllEndDatesOfRoom(int id){
        return StringToDateConverter.convertArrayOfStringsToArrayOfDates(
                reservingRepository.findAllEndsOfThisRoom(id));
    }

}
