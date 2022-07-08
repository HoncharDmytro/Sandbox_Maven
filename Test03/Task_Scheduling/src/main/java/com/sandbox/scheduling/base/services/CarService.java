package com.sandbox.scheduling.base.services;

import com.sandbox.scheduling.base.entities.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    void save(Car car);

    void updateCarAgeJob();

    boolean isDone();
}