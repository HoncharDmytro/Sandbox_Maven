package com.sandbox.remoting.http;

import com.sandbox.remoting.http.config.RmiClientConfig;
import com.sandbox.remoting.http.entities.Singer;
import com.sandbox.remoting.http.services.SingerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = RmiClientConfig.class)
@ExtendWith(SpringExtension.class)//@RunWith(SpringRunner.class) - jUnit4, forget it
@Slf4j
@AllArgsConstructor
public class RmiTests {
    private final SingerService singerService;

    @Test
    public void testRmiAll() {
        List<Singer> singers = singerService.findAll();
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void testRmiJohn() {
        List<Singer> singers = singerService.findByFirstName("John");
        assertEquals(2, singers.size());
        listSingers(singers);
    }

    private void listSingers(List<Singer> singers){
        singers.forEach(s -> log.info(s.toString()));
    }
}