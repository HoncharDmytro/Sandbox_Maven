package com.sandbox.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Slf4j
@Configuration
@PropertySource("classpath:properties/app.properties")
@ComponentScan(basePackages = "com.sandbox")
@EnableJdbcRepositories(basePackages = {"com.sandbox.repository"})
public class AppConfig {

    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public BasicDataSource dataSource() {
        try {
            //DriverManagerDataSource dataSource = new DriverManagerDataSource();
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return dataSource;
        } catch (Exception e) {
            log.error("BasicDataSource bean cannot be created!", e);
            return null;
        }
    }

}