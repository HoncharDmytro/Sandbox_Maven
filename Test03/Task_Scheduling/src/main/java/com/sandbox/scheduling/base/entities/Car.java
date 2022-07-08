package com.sandbox.scheduling.base.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;

    @Column(name="license_plate")
    private String licensePlate;

    private String manufacturer;

    @Column(name="manufacture_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private DateTime manufactureDate;

    private int age;

    @Version
    private int version;

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("{License: %s, Manufacturer: %s, Manufacture Date: %s, Age: %d}",
                licensePlate, manufacturer, sdf.format(manufactureDate.toDate()), age);
    }
}