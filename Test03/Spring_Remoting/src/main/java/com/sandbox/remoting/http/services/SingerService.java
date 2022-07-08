package com.sandbox.remoting.http.services;

import com.sandbox.remoting.http.entities.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();

    List<Singer> findByFirstName(String firstName);

    Singer findById(Long id);

    void save(Singer singer);

    void delete(Singer singer);
}
