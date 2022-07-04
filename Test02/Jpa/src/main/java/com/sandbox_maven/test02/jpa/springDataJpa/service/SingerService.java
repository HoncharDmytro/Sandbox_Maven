package com.sandbox_maven.test02.jpa.springDataJpa.service;

import com.sandbox_maven.test02.jpa.crud.entities.Singer;

import java.util.List;

// Spring Data use Repository!!!
public interface SingerService {
    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    List<Singer> findByFirstNameAndLastName(String firstName, String lastName);
}