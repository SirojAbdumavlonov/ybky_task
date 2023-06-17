package com.example.ybky.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    private String name;
    private String type;
    private int capacity;
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "room",
            cascade = CascadeType.ALL)
    private List<Time> time;

}
