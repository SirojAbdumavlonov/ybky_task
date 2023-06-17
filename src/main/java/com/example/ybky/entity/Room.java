package com.example.ybky.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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
