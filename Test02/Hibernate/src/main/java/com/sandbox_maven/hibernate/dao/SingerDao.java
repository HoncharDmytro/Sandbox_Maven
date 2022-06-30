package com.sandbox_maven.hibernate.dao;

import com.sandbox_maven.hibernate.entities.Singer;

import java.util.List;

public interface SingerDao {
    List<Singer> findAll();
    void save(Singer singer);
    void delete(Singer singer);
//////////////////////////////////////////NamedQueries////////////////////////////////////////////////////////
    List<Singer> findAllWithAlbum();
    Singer findById(Long id);
}