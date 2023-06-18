package com.example.ybky.service;

import com.example.ybky.entity.Room;
import com.example.ybky.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Optional<Room> findRoomById(int id){
        return roomRepository.findRoomById(id);
    }

}
