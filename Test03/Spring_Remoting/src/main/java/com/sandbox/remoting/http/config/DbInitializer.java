package com.sandbox.remoting.http.config;

import com.sandbox.remoting.http.entities.Singer;
import com.sandbox.remoting.http.repositories.SingerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.GregorianCalendar;

@Service
@Slf4j
@AllArgsConstructor
public class DbInitializer {
    SingerRepository singerRepository;

    public void initDB() {
        log.info("Starting database initialization ... ");
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date((new GregorianCalendar(1977, 9, 16))
                .getTime()
                .getTime()));
        singerRepository.save(singer);
        singer = new Singer();
        singer.setFirstName("Eric");
        singer.setLastName("Clapton");
        singer.setBirthDate(new Date((new GregorianCalendar(1945, 2, 30))
                .getTime()
                .getTime()));
        singerRepository.save(singer);
        singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Butler");
        singer.setBirthDate(new Date((new GregorianCalendar(1975, 3, 1))
                .getTime()
                .getTime()));
        singerRepository.save(singer);
        log.info("Database initialization finished.");
    }
}
