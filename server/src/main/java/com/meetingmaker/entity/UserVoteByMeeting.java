package com.meetingmaker.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("user_vote_by_meeting")
public class UserVoteByMeeting {

    @PrimaryKeyColumn(name = "meeting_id", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
    private UUID meetingId;

    @PrimaryKeyColumn(name = "candidate_id", type = PrimaryKeyType.CLUSTERED, ordinal = 2)
    private UUID candidateId;

    @PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.CLUSTERED, ordinal = 3)
    private UUID userId;

    @PrimaryKeyColumn(name = "created_at", type = PrimaryKeyType.CLUSTERED, ordinal = 2)
    private String createdAt;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    public UserVoteByMeeting(UUID meetingId, UUID candidateId, UUID userId, String firstName, String lastName) {
        this.meetingId = meetingId;
        this.candidateId = candidateId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(UUID meetingId) {
        this.meetingId = meetingId;
    }

    public UUID getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(UUID candidateId) {
        this.candidateId = candidateId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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
