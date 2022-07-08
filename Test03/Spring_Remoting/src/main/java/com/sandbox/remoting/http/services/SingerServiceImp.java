package com.sandbox.remoting.http.services;

import com.sandbox.remoting.http.entities.Singer;
import com.sandbox.remoting.http.repositories.SingerRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("singerService")
@AllArgsConstructor
public class SingerServiceImp implements SingerService{
    private final SingerRepository singerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return new ArrayList(Arrays.asList(singerRepository.findAll()));
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        return singerRepository.findByFirstName(firstName);
    }

    @Override
    public Singer findById(Long id) {
        return singerRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Singer singer) {
        singerRepository.save(singer);
    }

    @Override
    public void delete(Singer singer) {
        singerRepository.delete(singer);
    }
}
