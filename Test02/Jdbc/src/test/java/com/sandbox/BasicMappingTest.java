package com.sandbox;

import com.sandbox.config.AppConfig;
import com.sandbox.entities.Person;
import com.sandbox.repositories.PersonRepository;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // to run tests in order
public class BasicMappingTest {

    private static GenericApplicationContext ctx;
    //private static PersonService personService; //!!!!!!!!!!!!!!!!!!!!!!!
    private static PersonRepository personRepository;//!!!!!!!!!!!!!!!!!!!!!!!

    @BeforeAll
    private static void beforeAll() {
        //private JdbcTemplate jdbcTemplate;
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        personRepository = ctx.getBean(PersonRepository.class);
    }

    @Test
    @DisplayName("Create-Person-Test ")
    @Order(1)
    void createPersonTest() {
        Person created = personRepository.save(createPerson());
        assertNotNull(created);
    }

    @Test
    @DisplayName("Update-Person-Test ")
    @Order(2)
    void updatePersonTest() {
        List<Person> allUsers = (List<Person>) personRepository.findAll();

        allUsers.forEach(person -> {
            person.setLastName("Jillian");
            Person updated = personRepository.save(person);

            assertEquals(updated.getLastName(), "Jillian");
        });

        //personRepository.saveAll(allUsers); // batch update
    }

    @Test
    @DisplayName("Delete-Person-Test ")
    @Order(3)
    void deleteUserTest() {
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
        p.setLastName("Geffen");
        return p;
    }

    @AfterAll
    private static void afterAll() {
        ctx.close();
    }
}