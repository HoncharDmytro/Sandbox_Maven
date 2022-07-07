package com.sandbox.validation;

import com.sandbox.validation.config.AppConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

public class ValidationTest {
    private final static Logger logger = LoggerFactory.getLogger(ValidationTest.class);
    private static GenericApplicationContext ctx;
    private static Validator singerValidator;

    @BeforeAll
    private static void beforeTest() {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        singerValidator = ctx.getBean("singerValidator", Validator.class);
    }

    @AfterAll
    private static void afterTest() {
        ctx.close();
    }

    @Test
    public void validTest() {
        Singer singer = new Singer();
        singer.setFirstName(null);
        singer.setLastName("Mayer");

        BeanPropertyBindingResult result = new BeanPropertyBindingResult(singer, "John");

        ValidationUtils.invokeValidator(singerValidator, singer, result);

        List<ObjectError> errors = result.getAllErrors();
        logger.info("Number of errors in validation: " + errors.size());
        errors.forEach(e -> logger.info(e.getCode()));
    }
}
