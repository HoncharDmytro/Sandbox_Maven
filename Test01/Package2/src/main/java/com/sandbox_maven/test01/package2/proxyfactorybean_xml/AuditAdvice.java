package com.sandbox_maven.test01.package2.proxyfactorybean_xml;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

public class AuditAdvice implements MethodBeforeAdvice {

    @Override
    public void before(@Nullable Method method, @Nullable Object[] args, Object target) {
        System.out.println("Executing: " + method);
    }
}