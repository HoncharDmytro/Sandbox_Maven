package com.sandbox_maven.package3.aop;

import com.sandbox_maven.package3.entity.Book;
import com.sandbox_maven.package3.util.CustomResponse;
import com.sandbox_maven.package3.util.CustomStatus;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class MyAspect {
    @Around("Pointcuts.allAddMethods()")
    public Object aroundAddingAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Book book = null;

        if (methodSignature.getName().equals("addBook")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Book) {
                    book = (Book) arg;
                    log.info("Trying to add book with title {}", book.getTitle());
                }
            }
        }

        Object result = null;

        try {
            result = joinPoint.proceed();
        }
        catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }

        log.info("Added book with title {}", book.getTitle());
        return result;
    }

    @Around("Pointcuts.allGetMethods()")
    public Object aroundGettingAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String title = null;

        if (methodSignature.getName().equals("getAll")) {
            log.info("Trying to get all books");
        }
        else if (methodSignature.getName().equals("getBookByTitle")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof String) {
                    title = (String) arg;
                    log.info("Trying to get book with title {}", title);
                }
            }
        }

        Object result = null;

        try {
            result = joinPoint.proceed();
        }
        catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }

        if (methodSignature.getName().equals("getAll")) {
            log.info("All books has been got");
        }
        else if (methodSignature.getName().equals("getBookByTitle")) {
            log.info("Book with title {} has been got", title);
        }

        return result;
    }
}
