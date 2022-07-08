package com.sandbox.remoting.boot_rest.services;

import com.sandbox.remoting.boot_rest.entities.Singer;

import java.util.List;

public interface SingerService {

    List<Singer> findAll();

    List<Singer> findByFirstName(String firstName);

    Singer findById(Long id);

    Singer save(Singer singer);

    void delete(Singer singer);
}