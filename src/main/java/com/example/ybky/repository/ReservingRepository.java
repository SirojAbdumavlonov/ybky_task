package com.example.ybky.repository;

import com.example.ybky.entity.Reserving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservingRepository extends JpaRepository<Reserving, Integer> {
    @Query(
           "SELECT t.end FROM Reserving t WHERE t.room.id = ?1"
    )
    List<String> findAllEndsOfThisRoom(int id);


    @Query(
            "SELECT t.start FROM Reserving t WHERE t.room.id = ?1"
    )
    List<String> findAllStartsOfThisRoom(int id);

    @Query(
            "SELECT t.start FROM Reserving t WHERE t.room.id = ?1 AND t.start LIKE ?2%"
    )
    List<String> findAllStartsByIdAndDate(int roomId, String date);
    @Query(
            "SELECT t.end FROM Reserving t WHERE t.room.id = ?1 AND t.end LIKE ?2% "
    )
    List<String> findAllEndsByIdAndDate(int roomId, String date);

}
