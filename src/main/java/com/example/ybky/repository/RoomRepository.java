package com.example.ybky.repository;

import com.example.ybky.entity.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    Room findById(int id);
    Optional<Room> findRoomById(int id);

    @Query(
            "SELECT r FROM Room r"
    )
    List<Room> findAllRooms();

    List<Room> findAllByName(String name,
                             Pageable pageable);
    List<Room> findAllByType(String type,
                             Pageable pageable);
    List<Room> findAllByTypeAndName(String type,
                                    String name,
                                    Pageable pageable);



}
