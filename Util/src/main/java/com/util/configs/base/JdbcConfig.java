package com.util.configs.base;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.sql.DataSource;

public abstract class JdbcConfig {
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${driverClassName}")
    public String driverClassName;
    @Value("${url}")
    public String url;
    @Value("${user}")
    public String username;
    @Value("${password}")
    public String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @SuppressWarnings("unchecked")
    @Bean
    public DataSource dataSource() {
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return dataSource;
        } catch (Exception e) {
            logger.error("DataSource bean cannot be created!", e);
            return null;
        }
    }
}
