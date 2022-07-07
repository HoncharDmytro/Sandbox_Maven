package com.sandbox.transactions.jta.services;

import com.sandbox.transactions.jta.entities.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();

    Singer findById(Long id);

    void save(Singer singer);

    long countAll();
}