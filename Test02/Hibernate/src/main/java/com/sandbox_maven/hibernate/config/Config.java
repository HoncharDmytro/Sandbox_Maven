package com.sandbox_maven.hibernate.config;

import com.util.configs.hibernate.HibernateConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:properties/app.properties")
@ComponentScan(basePackages = {"com.sandbox_maven.hibernate.dao", "com.sandbox_maven.hibernate.config"})
@EnableTransactionManagement
public class Config extends HibernateConfig {

}