package com.sandbox.scheduling.base.tasks;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class TaskToExecute {
    private final TaskExecutor taskExecutor;

    public void executeTask() {
        for(int i = 0; i < 10; ++i) {
            taskExecutor.execute(() ->
                    log.info("Hello from thread: " + Thread.currentThread().getName()));
        }
    }
}