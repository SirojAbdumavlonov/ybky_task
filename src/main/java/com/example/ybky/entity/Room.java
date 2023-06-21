package com.example.ybky.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Room {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String type;
    private int capacity;


}
