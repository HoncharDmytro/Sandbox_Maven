package com.sandbox.testing.jupiter;

import com.sandbox.remoting.boot_rest.entities.Singer;
import com.sandbox.remoting.boot_rest.services.SingerService;
import com.sandbox.testing.jupiter.config.Config;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(classes = {Config.class})//ServiceConfig.class, DataConfig.class
@DisplayName("Integration SingerService Test")
@ActiveProfiles("test")
public class SingerServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(SingerServiceTest.class);

    SingerService singerService;

    public SingerServiceTest(SingerService singerService) {
        this.singerService = singerService;
    }

    @BeforeAll
    static void setUp() {
        logger.info("--> @BeforeAll - executes before executing all test methods in this class");
    }

    @AfterAll
    static void tearDown(){
        logger.info("--> @AfterAll - executes before executing all test methods in this class");
    }

    @BeforeEach
    void init() {
        logger.info("--> @BeforeEach - executes before each test method in this class");
    }

    @AfterEach
    void dispose() {
        logger.info("--> @AfterEach - executes after each test method in this class");
    }

    @Test
    @DisplayName("should return all singers")
    @SqlGroup({
            @Sql(value = "classpath:db/test-data.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:db/clean-up.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
    })
    public void findAll() {
        List<Singer> result = singerService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("should return singer 'John Mayer'")
    @SqlGroup({
            @Sql(value = "classpath:db/test-data.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:db/clean-up.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
    })
    public void testFindByFirstNameAndLastNameOne() throws Exception {
        Singer result = singerService.findByFirstNameAndLastName("John", "Mayer");
        assertNotNull(result);
    }
}