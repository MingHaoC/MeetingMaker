package com.meetingmaker.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "email_verify")
public class EmailVerify {

    @Id
    private String uuid;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName="id")
    private User user;

}
