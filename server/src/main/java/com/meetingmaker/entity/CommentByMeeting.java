package com.meetingmaker.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("comment_by_meeting")
public class CommentByMeeting {

    @PrimaryKeyColumn(name = "meeting_id", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
    private UUID meeetingId;

    @PrimaryKeyColumn(name = "comment_id", type = PrimaryKeyType.PARTITIONED, ordinal = 2)
    private UUID commentId;

    @PrimaryKeyColumn(name = "created_at", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
    private Date createdAt;

    @Column("user_id")
    private String userID;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("updated_at")
    private String updatedAt;

    public CommentByMeeting(UUID meeetingId, UUID commentId, String userID, String firstName, String lastName) {
        this.meeetingId = meeetingId;
        this.commentId = commentId;
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getMeeetingId() {
        return meeetingId;
    }

    public void setMeeetingId(UUID meeetingId) {
        this.meeetingId = meeetingId;
    }

    public UUID getCommentId() {
        return commentId;
    }

    public void setCommentId(UUID commentId) {
        this.commentId = commentId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
