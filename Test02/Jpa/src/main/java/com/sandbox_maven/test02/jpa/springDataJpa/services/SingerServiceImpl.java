package com.sandbox_maven.test02.jpa.springDataJpa.services;

import com.sandbox_maven.test02.jpa.crud.entities.Singer;
import com.sandbox_maven.test02.jpa.springDataJpa.repositories.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("springJpaSingerService")
@Transactional
public class SingerServiceImpl implements SingerService{
    private final SingerRepository singerRepository;

    @Autowired
    public SingerServiceImpl(@Qualifier("singerRepository") SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    @Transactional(readOnly=true)
    public List<Singer> findAll() {
        return new ArrayList(Arrays.asList(singerRepository.findAll()));
        //return new ArrayList(Collections.singletonList(singerRepository.findAll()));
    }

    @Transactional(readOnly=true)
    public List<Singer> findByFirstName(String firstName) {
        return singerRepository.findByFirstName(firstName);
    }

    @Transactional(readOnly=true)
    public List<Singer> findByFirstNameAndLastName(String firstName, String lastName) {
        return singerRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
