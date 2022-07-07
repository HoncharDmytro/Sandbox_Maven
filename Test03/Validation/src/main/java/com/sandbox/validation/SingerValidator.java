package com.sandbox.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("singerValidator")
public class SingerValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Singer.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "firstName", "firstName.empty");
    }
}