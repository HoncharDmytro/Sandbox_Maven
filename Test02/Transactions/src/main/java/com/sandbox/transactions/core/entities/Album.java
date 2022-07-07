package com.sandbox.transactions.core.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    private String title;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name="singer_id")
    private Singer singer;
}