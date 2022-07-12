package com.sandbox.web.base;

import com.sandbox.web.base.entities.Singer;
import com.sandbox.web.base.repositories.SingerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Dmytro Honchar
 * Date: 7/12/2022
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_CLASS)
public class SingerRepoTest {
    private SingerRepository singerRepository;

    @Autowired
    public void setSingerRepository(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    @Test
    public void testSaveSinger(){
        Singer singer = new Singer();
        singer.setFirstName("Angus");
        singer.setLastName("Young");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1991, 2, 17)).getTime().getTime()));
        singerRepository.save(singer);

        //get all singers, list should have 15
        Iterable<Singer> singers = singerRepository.findAll();
        int count = 0;

        for(Singer s : singers){
            count++;
        }
        assertEquals(count, 15);
    }
}