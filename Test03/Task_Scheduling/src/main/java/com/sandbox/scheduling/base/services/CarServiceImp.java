package com.sandbox.scheduling.base.services;

import com.sandbox.scheduling.base.entities.Car;
import com.sandbox.scheduling.base.repos.CarRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("carService")
@Repository
@Transactional
@Slf4j
@AllArgsConstructor
public class CarServiceImp implements CarService {
    public boolean done;
    private final CarRepository carRepository;

    @Override
    @Transactional(readOnly=true)
    public List<Car> findAll() {
        return new ArrayList(Arrays.asList(carRepository.findAll()));
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    @Scheduled(fixedDelay=10000)
    public void updateCarAgeJob() {
        List<Car> cars = findAll();

        DateTime currentDate = DateTime.now();
        log.info("Car age update job started");

        cars.forEach(car -> {
            int age = Years.yearsBetween(car.getManufactureDate(), currentDate).getYears();

            car.setAge(age);
            save(car);
            log.info("Car age update --> " + car);
        });

        log.info("Car age update job completed successfully");
    }

    @Override
    public boolean isDone() {
        return done;
    }
}