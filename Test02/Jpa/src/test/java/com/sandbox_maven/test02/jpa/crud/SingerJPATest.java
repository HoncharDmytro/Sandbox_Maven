package com.sandbox_maven.test02.jpa.crud;

import com.sandbox_maven.test02.jpa.crud.config.JpaConfig;
import com.sandbox_maven.test02.jpa.crud.entities.Album;
import com.sandbox_maven.test02.jpa.crud.entities.Singer;
import com.sandbox_maven.test02.jpa.crud.services.SingerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingerJPATest {
    private static final Logger logger = LoggerFactory.getLogger(SingerJPATest.class);

    private static GenericApplicationContext ctx;
    private static SingerService singerService;

    @BeforeAll
    static void setUp(){
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        assertNotNull(singerService);
    }

    @Test
    public void testFindAll(){
        List<Singer> singers = singerService.findAll();
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindAllWithAlbum(){
        List<Singer> singers = singerService.findAllWithAlbum();
        assertEquals(3, singers.size());
        listSingersWithAlbum(singers);
    }

    private static void listSingers(List<Singer> singers) {
        logger.info(" ---- Listing singers:");
        singers.forEach(s -> logger.info(s.toString()));
    }

    private static void listSingersWithAlbum(List<Singer> singers) {
        logger.info(" ---- Listing singers with instruments:");
        singers.forEach(s -> {
            logger.info(s.toString());
            if (s.getAlbums() != null) {
                s.getAlbums().forEach(a -> logger.info("\t" + a.toString()));
            }
            if (s.getInstruments() != null) {
                s.getInstruments().forEach(i -> logger.info("\tInstrument: " + i.getInstrumentId()));
            }
        });
    }

    @Test
    public void testInsert(){
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1940, 8, 16)).getTime().getTime()));

        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
        singer.addAlbum(album);

        album = new Album();
        album.setTitle("A Heart Full of Blues");
        album.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
        singer.addAlbum(album);

        singerService.save(singer);
        assertNotNull(singer.getId());

        List<Singer> singers = singerService.findAllWithAlbum();
        assertEquals(4, singers.size());
        listSingersWithAlbum(singers);
    }

    @Test
    public void testUpdate(){
        Singer singer = singerService.findById(1L);

        assertNotNull(singer);
        assertEquals("Mayer", singer.getLastName());

        Album album = singer.getAlbums().stream().filter(a -> a.getTitle()
                .equals("Battle Studies"))
                .findFirst()
                .orElse(null);

        singer.setFirstName("John Clayton");
        singer.removeAlbum(album);
        singerService.save(singer);

        listSingersWithAlbum(singerService.findAllWithAlbum());
    }

    @Test
    public void testDelete(){
        Singer singer = singerService.findById(2L);

        assertNotNull(singer);
        singerService.delete(singer);

        listSingersWithAlbum(singerService.findAllWithAlbum());
    }

    @Test
    public void testFindById(){
        Singer singer = singerService.findById(1L);
        assertNotNull(singer);
        assertEquals("Mayer", singer.getLastName());
        logger.info(singer.toString());
    }

    @AfterAll
    static void tearDown(){
        ctx.close();
    }
}