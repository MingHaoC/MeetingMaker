package com.meetingmaker.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "votes")
public class Vote {

    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private int id;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name = "id")
    private int userId;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name = "id")
    private int candidateId;
}
