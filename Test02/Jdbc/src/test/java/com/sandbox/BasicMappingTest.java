package com.sandbox;

import com.sandbox.entities.Person;
import com.sandbox.repositorys.PersonRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // to run tests in order
public class BasicMappingTest { // extends BaseTest

    //private JdbcTemplate jdbcTemplate;
    private final PersonRepository personRepository;

    //@Autowired
    public BasicMappingTest(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Test
    @DisplayName("Create-Person-Test ")
    @Order(1)
    void createPersonTest() {

        Person created = personRepository.save(createPerson());

        assertTrue(created != null && created.getId() != null);
    }

    @Test
    @DisplayName("Update-Person-Test ")
    @Order(2)
    void updatePersonTest() {

        // Read all users
        List<Person> allUsers = (List<Person>) personRepository.findAll();

        allUsers.forEach(person -> {
            person.setLastName("Jiligan");
            Person updated = personRepository.save(person);

            assertTrue(updated.getLastName().equals("Jiligan"));
        });

        //personRepository.saveAll(allUsers); // batch update
    }

    @Test
    @DisplayName("Delete-Person-Test ")
    @Order(3)
    void deleteUserTest() {

        // Read all users
        List<Person> allPersons = (List<Person>) personRepository.findAll();

        allPersons.forEach(person -> {
            personRepository.delete(person);

            assertTrue(personRepository.findById(person.getId()).isEmpty());
        });

        //personRepository.deleteAll(allPersons); //batch delete
    }

    public Person createPerson() {
        Person p = new Person();
        p.setFirstName("Bob");
        p.setLastName("Leffen");
        return p;
    }
}