package com.sandbox_maven.test02.jpa.springDataJpa;

import com.sandbox_maven.test02.jpa.crud.entities.Singer;
import com.sandbox_maven.test02.jpa.springDataJpa.repositories.SingerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com.sandbox_maven.test02.jpa.springDataJpa.config")
public class Application implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    SingerRepository singerRepository;

    @Autowired
    public Application(@Qualifier("singerRepository") SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }


    public static void main(String... args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        System.in.read();
        ctx.close();
    }

    @Transactional(readOnly = true)
    @Override public void run(String... args) throws Exception {
        List<Singer> singers = singerRepository.findByFirstName("John");
        listSingersWithAlbum(singers);
    }

    private static void listSingersWithAlbum(List<Singer> singers) {
        logger.info(" ---- Listing singers with instruments:");
        singers.forEach(singer -> {
            logger.info(singer.toString());
            if (singer.getAlbums() != null) {
                singer.getAlbums().forEach(album -> logger.info("\t" + album.toString()));
            }
            if (singer.getInstruments() != null) {
                singer.getInstruments().forEach(instrument -> logger.info("\t" + instrument.getInstrumentId()));
            }
        });
    }
}