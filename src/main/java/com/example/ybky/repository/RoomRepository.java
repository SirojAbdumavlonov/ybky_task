package com.example.ybky.repository;

import com.example.ybky.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    Room findById(int id);


    Optional<Room> findRoomById(int id);
}
