package com.sandbox.remoting.http.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Singer implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Version
    private int version;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;
}
