package com.meetingmaker.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "candidates")
public class Candidate {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Meeting.class)
    @JoinColumn(name = "meeting_id")
    private int meetingId;

    @Column(name="date")
    private Date date;

    @Column(name="start_time")
    private LocalTime startTime;

    @Column(name="end_time")
    private LocalTime endTime;

}
