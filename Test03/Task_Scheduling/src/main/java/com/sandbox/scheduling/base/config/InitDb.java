package com.sandbox.scheduling.base.config;

import com.sandbox.scheduling.base.entities.Car;
import com.sandbox.scheduling.base.repos.CarRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@AllArgsConstructor
public class InitDb {
    private final CarRepository carRepository;

    public void initDB() {
        log.info("Starting database initialization...");
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

        Car car = new Car();
        car.setLicensePlate("GRAVITY-0405");
        car.setManufacturer("Ford");
        car.setManufactureDate(DateTime.parse("2006-09-12", formatter));
        carRepository.save(car);

        car = new Car();
        car.setLicensePlate("CLARITY-0432");
        car.setManufacturer("Toyota");
        car.setManufactureDate(DateTime.parse("2003-09-09", formatter));
        carRepository.save(car);

        car = new Car();
        car.setLicensePlate("ROSIE-0402");
        car.setManufacturer("Toyota");
        car.setManufactureDate(DateTime.parse("2017-04-16", formatter));
        carRepository.save(car);

        log.info("Database initialization finished.");
    }
}