package com.example.ybky.service;

import com.example.ybky.entity.Room;
import com.example.ybky.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;


    public Optional<Room> findRoomById(int id){
        return roomRepository.findRoomById(id);
    }

    public List<Room> findAllRoomsByParams(String name, String type,
                                            int page, int page_size){
        List<Room> rooms;

        Pageable recordsOnPage =
                PageRequest.of(page-1, page_size);//'page - 1' because page starts with 0 index

        if(name != null && type != null){
            rooms = roomRepository.findAllByTypeAndName(type, name, recordsOnPage);
        }
        else if (type != null) {
            rooms = roomRepository.findAllByType(type, recordsOnPage);
        }
        else if (name != null) {
            rooms = roomRepository.findAllByName(name, recordsOnPage);
        }
        else {
            rooms = roomRepository.findAllRooms();
        }
        return rooms;
    }



}
