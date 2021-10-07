package com.meetingmaker.entity;

import com.meetingmaker.constant.RoleName;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    public RoleName getName() {
        return name;
    }

}