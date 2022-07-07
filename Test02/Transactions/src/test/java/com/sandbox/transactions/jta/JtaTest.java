package com.sandbox.transactions.jta;

import com.sandbox.transactions.jta.config.ServicesConfig;
import com.sandbox.transactions.jta.config.XAJpaConfig;
import com.sandbox.transactions.jta.entities.Singer;
import com.sandbox.transactions.jta.services.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Slf4j
public class JtaTest {
    private static GenericApplicationContext ctx;
    private static SingerService singerService;

    @BeforeAll
    private static void beforeTest() {
        ctx = new AnnotationConfigApplicationContext(ServicesConfig.class, XAJpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        Assertions.assertNotNull(singerService);
    }

    @AfterAll
    private static void afterTest() {
        ctx.close();
    }

    @Test
    private void saveTest() {
        Singer singer = new Singer();
        singer.setFirstName("Bob");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date((new GregorianCalendar(1977, 9, 16))
                        .getTime()
                        .getTime()));

        singerService.save(singer);

        if (singer.getId() != null) {
            log.info("--> Singer saved successfully");
        }
        else {
            log.info("--> Singer was not saved, check the configuration!");
        }
    }

    @Test
    private void findAllTest() {
        List<Singer> singers = singerService.findAll();
        if (singers.size() != 2) {
            log.error("--> Something went wrong.");
        } else {
            log.info("--> Singers from both DBs: " + singers);
        }
    }
}
