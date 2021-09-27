package com.meetingmaker.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Table("candidate_time_by_meeting")
public class CandidateTimeByMeeting {

    @PrimaryKeyColumn(name = "meeting_id", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
    private UUID meetingId;

    @PrimaryKeyColumn(name = "date", type = PrimaryKeyType.PARTITIONED, ordinal = 1)
    private Date date;

    @PrimaryKeyColumn(name = "start_time", type = PrimaryKeyType.PARTITIONED, ordinal = 2)
    private LocalTime startTime;

    @PrimaryKeyColumn(name = "end_time", type = PrimaryKeyType.PARTITIONED, ordinal = 3)
    private LocalTime endTime;

    @Column("candidate_id")
    private UUID candidateId;

    public CandidateTimeByMeeting(UUID meetingId, UUID candidateId, Date date, LocalTime startTime, LocalTime endTime) {
        this.meetingId = meetingId;
        this.candidateId = candidateId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
