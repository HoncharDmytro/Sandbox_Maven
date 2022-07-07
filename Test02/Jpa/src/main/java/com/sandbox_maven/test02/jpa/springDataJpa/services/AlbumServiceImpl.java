package com.sandbox_maven.test02.jpa.springDataJpa.services;

import com.sandbox_maven.test02.jpa.crud.entities.Album;
import com.sandbox_maven.test02.jpa.crud.entities.Singer;
import com.sandbox_maven.test02.jpa.springDataJpa.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("springJpaAlbumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(@Qualifier("albumRepository") AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Transactional(readOnly=true)
    @Override public List<Album> findBySinger(Singer singer) {
        return albumRepository.findBySinger(singer);
    }

    @Override public List<Album> findByTitle(String title) {
        return albumRepository.findByTitle(title);
    }
}