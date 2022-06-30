package com.sandbox_maven.hibernate.config;

import com.sandbox_maven.hibernate.dao.InstrumentDao;
import com.sandbox_maven.hibernate.dao.SingerDao;
import com.sandbox_maven.hibernate.entities.Album;
import com.sandbox_maven.hibernate.entities.Instrument;
import com.sandbox_maven.hibernate.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class Initializer {
    private final Logger logger = LoggerFactory.getLogger(Initializer.class);

    SingerDao singerDao;

    @Autowired
    public void setSingerDao(SingerDao singerDao) {
        this.singerDao = singerDao;
    }

    InstrumentDao instrumentDao;

    @Autowired
    public void setInstrumentDao(InstrumentDao instrumentDao) {
        this.instrumentDao = instrumentDao;
    }

    //@PostConstruct
    public final void initDB() {
        logger.info("Starting database initialization...");

        Instrument guitar = new Instrument();
        guitar.setInstrumentId("Guitar");
        instrumentDao.save(guitar);

        Instrument piano = new Instrument();
        piano.setInstrumentId("Piano");
        instrumentDao.save(piano);

        Instrument voice = new Instrument();
        voice.setInstrumentId("Voice");
        instrumentDao.save(voice);

        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1977, Calendar.OCTOBER, 16)).getTime().getTime()));
        singer.addInstrument(guitar);
        singer.addInstrument(piano);

        Album album1 = new Album();
        album1.setTitle("The Search For Everything");
        album1.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(2017, Calendar.JANUARY, 20)).getTime().getTime()));
        singer.addAlbum(album1);

        Album album2 = new Album();
        album2.setTitle("Battle Studies");
        album2.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(2009, Calendar.NOVEMBER, 17)).getTime().getTime()));
        singer.addAlbum(album2);

        singerDao.save(singer);

        singer = new Singer();
        singer.setFirstName("Eric");
        singer.setLastName("Clapton");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1945, Calendar.MARCH, 30)).getTime().getTime()));
        singer.addInstrument(guitar);

        Album album = new Album();
        album.setTitle("From The Cradle");
        album.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(1994, Calendar.SEPTEMBER, 13)).getTime().getTime()));
        singer.addAlbum(album);

        singerDao.save(singer);

        singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Butler");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1975, Calendar.APRIL, 1)).getTime().getTime()));
        singer.addInstrument(guitar);

        singerDao.save(singer);
        logger.info("Database initialization finished.");
    }

}