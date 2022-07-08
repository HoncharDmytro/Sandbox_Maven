package com.sandbox.remoting.boot_rest;

import com.sandbox.remoting.boot_rest.entities.Singer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class RestClientTest {

    final Logger logger = LoggerFactory.getLogger(RestClientTest.class);
    private static final String URL_GET_ALL_SINGERS = "http://localhost:8080/singer/listdata";
    private static final String URL_GET_SINGER_BY_ID = "http://localhost:8080/singer/{id}";
    private static final String URL_CREATE_SINGER = "http://localhost:8080/singer/";
    private static final String URL_UPDATE_SINGER = "http://localhost:8080/singer/{id}";
    private static final String URL_DELETE_SINGER = "http://localhost:8080/singer/{id}";
    private static RestTemplate restTemplate;

    @BeforeAll
    static void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testFindAll() {
        logger.info("--> Testing retrieve all singers");
        Singer[] singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singer[].class);
        assert singers != null;
        assertEquals(singers.length, 3);
        listSingers(singers);
    }

    @Test
    public void testFindById() {
        logger.info("--> Testing retrieve a singer by id : 1");
        Singer singer = restTemplate.getForObject(URL_GET_SINGER_BY_ID, Singer.class, 1);
        assertNotNull(singer);
        logger.info(singer.toString());
    }

    @Test
    public void testUpdate() {
        logger.info("--> Testing update singer by id : 1");
        Singer singer = restTemplate.getForObject(URL_UPDATE_SINGER, Singer.class, 1);
        assert singer != null;
        singer.setFirstName("John Clayton");
        restTemplate.put(URL_UPDATE_SINGER, singer, 1);
        logger.info("Singer update successfully: " + singer);
    }

    @Test
    public void testDelete() {
        logger.info("--> Testing delete singer by id : 3");
        restTemplate.delete(URL_DELETE_SINGER, 3);
        Singer[] singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singer[].class);
        boolean found = false;
        assert singers != null;
        for (Singer s : singers) {
            if (s.getId() == 3) {
                found = true;
                break;
            }
        }
        assertFalse(found);
        listSingers(singers);
    }

    @Test
    public void testCreate() {
        logger.info("--> Testing create singer");
        Singer singerNew = new Singer();
        singerNew.setFirstName("BB");
        singerNew.setLastName("King");
        singerNew.setBirthDate(new Date(
                (new GregorianCalendar(1940, Calendar.SEPTEMBER, 16))
                        .getTime()
                        .getTime()));
        singerNew = restTemplate.postForObject(URL_CREATE_SINGER, singerNew, Singer.class);
        logger.info("Singer created successfully: " + singerNew);
    }

    private void listSingers(Singer[] singers) {
        Arrays.stream(singers).forEach(s -> logger.info(s.toString()));
    }
}