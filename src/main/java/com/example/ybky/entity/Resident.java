package com.example.ybky.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resident {
    @Id
    @SequenceGenerator(
            name = "resident_sequence",
            sequenceName = "resident_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "resident_sequence"
    )
    private int residentId;
    private String name;

}
