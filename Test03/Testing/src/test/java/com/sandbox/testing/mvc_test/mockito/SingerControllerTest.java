package com.sandbox.testing.mvc_test.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.sandbox.remoting.boot_rest.entities.Singer;
import com.sandbox.remoting.boot_rest.services.SingerService;
import com.sandbox.testing.mvc_test.SingerController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

public class SingerControllerTest {
    private final List<Singer> singers = new ArrayList<>();

    @BeforeAll
    public void initSingers() {
        Singer singer = new Singer();
        singer.setId(1L);
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singers.add(singer);
    }

    @Test
    public void testList() throws Exception {
        SingerService singerService = mock(SingerService.class);
        when(singerService.findAll()).thenReturn(singers);

        SingerController singerController = new SingerController();

        ReflectionTestUtils.setField(singerController, "singerService", singerService);

        ExtendedModelMap uiModel = new ExtendedModelMap();
        uiModel.addAttribute("singers", singerController.listData());

        //Singers modelSingers = (Singers) uiModel.get("singers");

        //assertEquals(1, modelSingers.getSingers().size());
    }

    @Test
    public void testCreate() {
        final Singer newSinger = new Singer();
        newSinger.setId(999L);
        newSinger.setFirstName("Stevie");
        newSinger.setLastName("Vaughan");

        SingerService singerService = mock(SingerService.class);
        when(singerService.save(newSinger)).thenAnswer(invocation -> {
            singers.add(newSinger);
            return newSinger;
        });

        SingerController singerController = new SingerController();
        ReflectionTestUtils.setField(singerController, "singerService",
                singerService);

        Singer singer = singerController.create(newSinger);
        assertEquals(Long.valueOf(999L), singer.getId());
        assertEquals("Stevie", singer.getFirstName());
        assertEquals("Vaughan", singer.getLastName());

        assertEquals(2, singers.size());
    }
}