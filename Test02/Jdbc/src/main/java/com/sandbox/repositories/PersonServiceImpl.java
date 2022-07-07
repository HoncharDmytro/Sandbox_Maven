//package com.sandbox.repositorys;
//
//import com.sandbox.entities.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Service("springJdbcPersonService")
//public class PersonServiceImpl implements PersonService{
//    private final PersonRepository personRepository;
//
//    @Autowired
//    public PersonServiceImpl(PersonRepository personRepository) {
//        this.personRepository = personRepository;
//    }
//
//    @Override
//    public List<Person> findAll() {
//        return new ArrayList(Arrays.asList(personRepository.findAll()));
//        //return new ArrayList(Collections.singletonList(personRepository.findAll()));
//    }
//
//    @Override
//    public List<Person> findByFirstName(String firstName) {
//        return personRepository.findByFirstName(firstName);
//    }
//
//    @Override
//    public List<Person> findByFirstNameAndLastName(String firstName, String lastName) {
//        return personRepository.findByFirstNameAndLastName(firstName, lastName);
//    }
//
//    @Override
//    public boolean updateByFirstName(Long id, String name) {
//        return personRepository.updateByFirstName(id, name);
//    }
//}
