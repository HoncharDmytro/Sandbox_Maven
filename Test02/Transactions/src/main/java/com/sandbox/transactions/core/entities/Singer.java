package com.sandbox.transactions.core.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NamedQueries({
        @NamedQuery(name=Singer.FIND_ALL, query="select s from Singer s"),
        @NamedQuery(name=Singer.COUNT_ALL, query="select count(s) from Singer s")
})
public class Singer implements Serializable {
    public static final String FIND_ALL = "Singer.findAll";
    public static final String COUNT_ALL = "Singer.countAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "singer", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<Album> albums = new HashSet<>();

    public boolean addAlbum(Album album) {
        album.setSinger(this);
        return getAlbums().add(album);
    }

    public void removeAlbum(Album album) {
        getAlbums().remove(album);
    }
}