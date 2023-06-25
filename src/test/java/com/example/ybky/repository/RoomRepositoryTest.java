package com.example.ybky.repository;

import com.example.ybky.entity.Room;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class RoomRepositoryTest {
    @Autowired
    private RoomRepository roomRepository;
    Pageable records =
            PageRequest.of(0, 10);
    @AfterEach
    void tearDown() {
        roomRepository.deleteAll();
    }
    @Test
    void canFindByCapacity() {
        int capacity = 20;
        Room room = Room.builder()
                .type("focus")
                .name("uzum")
                .capacity(capacity)
                .build();
        roomRepository.save(room);
        Room expected = roomRepository.findByCapacity(capacity);
        //assertion for class core
        assertThat(room).isEqualTo(expected);
    }
    @Test
    void canFindAllRooms() {
        List<Room> allRooms =
                new ArrayList<>();
        Room one = Room.builder()
                .capacity(10)
                .name("ten")
                .type("team")
                .build();
        Room two = Room.builder()
                .capacity(20)
                .name("twenty")
                .type("conference")
                .build();
        roomRepository.save(one);
        roomRepository.save(two);
        allRooms.add(one);
        allRooms.add(two);
        List<Room> expected = roomRepository.findAllRooms();
        assertThat(allRooms).isEqualTo(expected);
    }
    @Test
    void findAllByName() {
        List<Room> allRoomsByNameOne =
                new ArrayList<>();
        List<Room> allRoomsByNameTwo =
                new ArrayList<>();
        Room one = Room.builder()
                .capacity(10)
                .name("ten")
                .type("team")
                .build();
        Room two = Room.builder()
                .capacity(20)
                .name("twenty")
                .type("conference")
                .build();
        roomRepository.save(one);
        roomRepository.save(two);
        allRoomsByNameOne.add(one);
        allRoomsByNameTwo.add(two);
        List<Room> expectedRoomsByNameOne = roomRepository.findAllByName(
                one.getName(), records);
        List<Room> expectedRoomsByNameTwo = roomRepository.findAllByName(
                two.getName(), records);
        assertThat(allRoomsByNameOne).isEqualTo(expectedRoomsByNameOne);
        assertThat(allRoomsByNameTwo).isEqualTo(expectedRoomsByNameTwo);
    }
    @Test
    void findAllByType() {
        List<Room> allRoomsByTypeOne =
                new ArrayList<>();
        List<Room> allRoomsByTypeTwo =
                new ArrayList<>();
        Room one = Room.builder()
                .capacity(10)
                .name("ten")
                .type("team")
                .build();
        Room two = Room.builder()
                .capacity(20)
                .name("twenty")
                .type("conference")
                .build();
        roomRepository.save(one);
        roomRepository.save(two);

        allRoomsByTypeOne.add(one);
        allRoomsByTypeTwo.add(two);

        List<Room> expectedRoomsByTypeOne =
                roomRepository.findAllByType(one.getType(), records);
        List<Room> expectedRoomsByTypeTwo =
                roomRepository.findAllByType(two.getType(), records);
        assertThat(allRoomsByTypeOne).isEqualTo(expectedRoomsByTypeOne);
        assertThat(allRoomsByTypeTwo).isEqualTo(expectedRoomsByTypeTwo);
    }

    @Test
    void findAllByTypeAndName() {

    }
}