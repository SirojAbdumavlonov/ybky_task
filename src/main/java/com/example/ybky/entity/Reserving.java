package com.example.ybky.entity;

import com.example.ybky.tools.StringToDateConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Reserving {
    @Id
    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "room_id"
    )
    private Room room;
    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "resident_id"
    )
    private Resident resident;


    @Temporal(TemporalType.DATE)
    private String start;

    @Temporal(TemporalType.DATE)
    private String end;

    public Date getStartLikeDate(){
        return StringToDateConverter.convertStringToDate(start);
    }
    public Date getEndLikeDate(){
        return StringToDateConverter.convertStringToDate(end);
    }

}
