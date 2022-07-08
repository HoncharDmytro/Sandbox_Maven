package com.sandbox.remoting.boot_rest.repos;

import com.sandbox.remoting.boot_rest.entities.Singer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SingerRepository extends CrudRepository<Singer, Long> {

    List<Singer> findByFirstName(String firstName);
}