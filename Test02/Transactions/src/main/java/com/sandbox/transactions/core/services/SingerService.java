package com.sandbox.transactions.core.services;

import com.sandbox.transactions.core.entities.Singer;

import java.util.List;
import java.util.Optional;

public interface SingerService {
    List<Singer> findAll();

    Optional<Singer> findById(Long id);

    void save(Singer singer);

    long countAll();
}