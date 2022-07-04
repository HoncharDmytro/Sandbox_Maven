package com.sandbox.entities;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Person {
    @Id
    private long id;
    private String firstName;
    private String lastName;
}
