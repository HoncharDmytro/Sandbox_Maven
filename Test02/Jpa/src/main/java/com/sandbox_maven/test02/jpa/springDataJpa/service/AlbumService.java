package com.sandbox_maven.test02.jpa.springDataJpa.service;

import com.sandbox_maven.test02.jpa.crud.entities.Album;
import com.sandbox_maven.test02.jpa.crud.entities.Singer;

import java.util.List;

public interface AlbumService {
    List<Album> findBySinger(Singer singer);

    List<Album> findByTitle(String title);
}