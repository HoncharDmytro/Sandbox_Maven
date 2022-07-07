package com.sandbox_maven.test02.jpa.springDataJpa.repositories;

import com.sandbox_maven.test02.jpa.crud.entities.Singer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("singerRepository")
// Spring Data use Repository!!!
public interface SingerRepository extends CrudRepository<Singer, Long> {

    //scan method name ent translate into query :
    // "select s from Singer s"
    // + "where с.firstName =: firstName"
    List<Singer> findByFirstName(String firstName);

    List<Singer> findByFirstNameAndLastName(String firstName, String lastName);
}