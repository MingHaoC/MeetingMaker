package com.meetingmaker.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("user_by_meeting")
public class UserByMeeting {

    @PrimaryKeyColumn(name = "meeting_id", type= PrimaryKeyType.PARTITIONED, ordinal = 0)
    private UUID meetingID;

    @PrimaryKeyColumn(name = "user_id", type= PrimaryKeyType.PARTITIONED, ordinal = 1)
    private UUID userID;

    @Column("email")
    private String email;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    public UserByMeeting(UUID meetingID, UUID userID, String email, String firstName, String lastName) {
        this.meetingID = meetingID;
        this.userID = userID;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(UUID meetingID) {
        this.meetingID = meetingID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
