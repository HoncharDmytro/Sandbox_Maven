package com.sandbox_maven.test02.jpa.springDataJpa;

import com.sandbox_maven.test02.jpa.crud.entities.Album;
import com.sandbox_maven.test02.jpa.crud.entities.Singer;
import com.sandbox_maven.test02.jpa.springDataJpa.config.DataJpaConfig;
import com.sandbox_maven.test02.jpa.springDataJpa.service.AlbumService;
import com.sandbox_maven.test02.jpa.springDataJpa.service.SingerService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SingerDataJPATest {
    private static final Logger logger = LoggerFactory.getLogger(SingerDataJPATest.class);

    private static GenericApplicationContext ctx;
    private static SingerService singerService;
    private static AlbumService albumService;

    @Test
    public void testFindAll(){
        List<Singer> singers = singerService.findAll();
        assertTrue(singers.size() > 0);
        listSingers(singers);
    }

    @Test
    public void testFindByFirstName(){
        List<Singer> singers = singerService.findByFirstName("John");
        assertTrue(singers.size() > 0);
        assertEquals(2, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindByFirstNameAndLastName(){
        List<Singer> singers = singerService.findByFirstNameAndLastName("John", "Mayer");
        assertTrue(singers.size() > 0);
        assertEquals(1, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindBySinger(){
        List<Singer> singers = singerService.findByFirstNameAndLastName("John", "Mayer");
        assertTrue(singers.size() > 0);
        assertEquals(1, singers.size());

        Singer singer = singers.get(0);
        List<Album> albums = albumService.findBySinger(singer);
        assertTrue(albums.size() > 0);
        assertEquals(2, albums.size());
        albums.forEach(a -> logger.info(a.toString()));
    }

    @Test
    public void testFindByTitle(){
        List<Album> albums = albumService.findByTitle("The");
        assertTrue(albums.size() > 0);
        assertEquals(2, albums.size());
        albums.forEach(a -> logger.info(a.toString() + ", Singer: "+ a.getSinger().getFirstName() + " "
                + a.getSinger().getLastName()));
    }

    @BeforeAll
    private static void setUp(){
        ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        albumService = ctx.getBean(AlbumService.class);
        assertNotNull(singerService);
        assertNotNull(albumService);
    }

    private static void listSingers(List<Singer> singers) {
        logger.info(" ---- Listing singers:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
        }
    }

    @AfterAll
    private static void tearDown() {
        ctx.close();
    }
}