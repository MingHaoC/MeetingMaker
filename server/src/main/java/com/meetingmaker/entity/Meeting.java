package com.meetingmaker.entity;


import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.Date;
import java.util.UUID;

public class Meeting {

    @PrimaryKeyColumn(name = "meeting_id", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
    private UUID meetingID;

    @PrimaryKeyColumn(name = "join_code", type = PrimaryKeyType.PARTITIONED, ordinal = 1)
    private String joinCode;

    @Column("title")
    private String title;

    @Column("description")
    private String description;

    @Column("due_date")
    private String dueDate;

    @Column("created_at")
    private Date createdAt;

    @Column("updated_at")
    private Date updatedAt;

    public Meeting(UUID meetingID, String joinCode, String title, String description, String dueDate) {
        this.meetingID = meetingID;
        this.joinCode = joinCode;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public UUID getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(UUID meetingID) {
        this.meetingID = meetingID;
    }

    public String getJoinCode() {
        return joinCode;
    }

    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

}