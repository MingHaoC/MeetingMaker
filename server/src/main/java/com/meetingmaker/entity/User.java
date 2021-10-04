package com.meetingmaker.entity;

import com.datastax.oss.driver.api.core.type.DataType;
import com.datastax.oss.protocol.internal.ProtocolConstants;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table("user")
@Setter
@Getter
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

    @Column("roles")
    @CassandraType(type = CassandraType.Name.SET,  typeArguments = { CassandraType.Name.TEXT })
    private Set<String> roles = new HashSet<>();

    public User(String id, String email, String password, String firstName, String lastName, Date createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.updatedAt = new Date(System.currentTimeMillis());

        this.roles.add("ROLE_USER");

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

}
