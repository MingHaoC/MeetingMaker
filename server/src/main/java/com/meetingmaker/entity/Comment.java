package com.meetingmaker.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "comments")
public class Comment {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Meeting.class)
    @JoinColumn(name = "meeting_id")
    private int meetingId;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private int userId;

    @Column(name = "message")
    private String message;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @CreationTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}
