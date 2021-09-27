package com.meetingmaker.entity;

import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.Date;

@Table("user")
@Data()
public class User {

    @PrimaryKeyColumn(name = "email", type = PrimaryKeyType.PARTITIONED)
    @Id
    private String email;

    @Column("password")
    private String password;

    @Column("user_id")
    private String id;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("created_at")
    private Date createdAt;

    @Column("update_at")
    private Date updatedAt;

    public User(String id, String email, String password, String firstName, String lastName, Date createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    /**
     * Hash password to be stored in the database
     *
     */
    public void hashPassword() {
        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
    }

    /**
     * Check if user inputted password (unhash) match with the hash password in the dbs
     *
     * @param password  plain text password (unhash)
     * @return true if both match else false
     */
    public boolean checkPass(String password) {
        return BCrypt.checkpw(password, this.password);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
