package com.sandbox.scheduling.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@Import(Config.class)
@EnableScheduling
public class BaseConfig {
    // decomment this to use the TaskScheduler explicitly declared bean

//	@Bean
//    TaskScheduler carScheduler() {
//		ThreadPoolTaskScheduler carScheduler = new ThreadPoolTaskScheduler();
//		carScheduler.setPoolSize(10);
//		return carScheduler;
//	}


//    @Bean
//    TaskExecutor taskExecutor() {
//        return new SimpleAsyncTaskExecutor();
//    }
}
