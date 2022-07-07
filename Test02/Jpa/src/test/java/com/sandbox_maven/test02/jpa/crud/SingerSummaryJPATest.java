package com.sandbox_maven.test02.jpa.crud;

import com.sandbox_maven.test02.jpa.crud.config.JpaConfig;
import com.sandbox_maven.test02.jpa.crud.modelView.SingerSummary;
import com.sandbox_maven.test02.jpa.crud.services.SingerSummaryService;
import com.sandbox_maven.test02.jpa.crud.services.SingerSummaryUntypeImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingerSummaryJPATest {

    private static final Logger logger = LoggerFactory.getLogger(SingerSummaryJPATest.class);
    private static GenericApplicationContext ctx;
    private static SingerSummaryService singerSummaryService;
    private static SingerSummaryUntypeImpl singerSummaryUntype;

    @BeforeAll
    static void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerSummaryService = ctx.getBean(SingerSummaryService.class);
        singerSummaryUntype = ctx.getBean(SingerSummaryUntypeImpl.class);
        assertNotNull(singerSummaryService);
        assertNotNull(singerSummaryUntype);
    }

    @Test
    public void testFindAll() {
        List<SingerSummary> singers = singerSummaryService.findAll();
        listSingerSummary(singers);
        assertEquals(2, singers.size());
    }

    @Test
    public void testFindAllUntype() {
        singerSummaryUntype.displayAllSingerSummary();
    }

    private static void listSingerSummary(List<SingerSummary> singers) {
        logger.info(" ---- Listing singers summary:");
        for (SingerSummary singer : singers) {
            logger.info(singer.toString());
        }
    }

    @AfterAll
    static void tearDown() {
        ctx.close();
    }
}