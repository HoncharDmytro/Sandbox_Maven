package com.sandbox.transactions.core.config;

import javax.persistence.EntityManagerFactory;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@AllArgsConstructor
@EnableTransactionManagement
@ComponentScan(basePackages = "com.sandbox.transactions.core")
public class ServicesConfig {
    EntityManagerFactory entityManagerFactory;

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory);
    }
}