package com.example.ybky.service;

import com.example.ybky.entity.Reserving;
import com.example.ybky.entity.Resident;
import com.example.ybky.entity.Room;
import com.example.ybky.repository.ReservingRepository;
import com.example.ybky.repository.RoomRepository;
import com.example.ybky.tools.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservingService {

    @Autowired
    private ReservingRepository reservingRepository;

    @Autowired
    private RoomRepository roomRepository;



    public void saveRoomWithResident(String start, String end, String residentName, int roomId){
        Resident resident =
                Resident.builder()
                        .name(residentName)
                        .build();
        Room room = roomRepository.findById(roomId);

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
        return Converter.convertArrayOfStringsToArrayOfDates(
                reservingRepository.findAllStartsOfThisRoom(id));
    }
    public List<Date> findAllEndDatesOfRoom(int id){
        return Converter.convertArrayOfStringsToArrayOfDates(
                reservingRepository.findAllEndsOfThisRoom(id));
    }

    public List<String> allStartsByIdAndDate(int roomId, String date){
        return reservingRepository.findAllStartsByIdAndDate(roomId, date);

    }
    public List<String> allEndsByIdAndDate(int roomId, String date){
        return reservingRepository.findAllEndsByIdAndDate(roomId, date);

    }





}
