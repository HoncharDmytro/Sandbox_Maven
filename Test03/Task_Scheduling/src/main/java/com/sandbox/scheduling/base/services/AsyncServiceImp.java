package com.sandbox.scheduling.base.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Slf4j
@Service("asyncService")
public class AsyncServiceImp implements AsyncService {
    @Async
    @Override
    public void asyncTask() {
        log.info("Start execution of async. task");

        try {
            Thread.sleep(10000);
        } catch (Exception ex) {
            log.error("Task Interruption", ex);
        }

        log.info("Complete execution of async. task");
    }

    @Async
    @Override
    public Future<String> asyncWithReturn(String name) {
        log.info("Start execution of async. task with return for "+ name);

        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
            log.error("Task Interruption", ex);
        }

        log.info("Complete execution of async. task with return for " + name);

        return new AsyncResult<>("Hello: " + name);
    }
}