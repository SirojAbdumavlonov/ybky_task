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
public class Reserving {
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "room_id"
    )
    private Room room;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "resident_id"
    )
    private Resident resident;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int booking_id;

    @Temporal(TemporalType.DATE)
    private String start;

    @Temporal(TemporalType.DATE)
    private String end;

}
