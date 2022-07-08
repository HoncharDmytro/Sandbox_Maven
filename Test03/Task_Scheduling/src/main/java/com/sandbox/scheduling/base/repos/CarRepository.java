package com.sandbox.scheduling.base.repos;

import com.sandbox.scheduling.base.entities.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}