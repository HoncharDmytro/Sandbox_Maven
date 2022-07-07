package com.sandbox_maven.test02.jpa.crud.config;

import javax.persistence.EntityManagerFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:properties/app.properties")
@ComponentScan(basePackages = {"com.sandbox_maven.test02.jpa.crud.services"})
public class JpaConfig {

    private static final Logger logger = LoggerFactory.getLogger(JpaConfig.class);

    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${user}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${dialect}")
    private String dialect;

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
            logger.error("BasicDataSource bean cannot be created!", e);
            return null;
        }
    }

//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new JpaTransactionManager(entityManagerFactory());
//    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        //return new HibernateJpaVendorAdapter();
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setShowSql(true);
        return jpaVendorAdapter;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", dialect);
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
        return hibernateProp;
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean =
//                new LocalContainerEntityManagerFactoryBean();
//        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
//        localContainerEntityManagerFactoryBean.setJpaProperties(hibernateProperties());
//        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
//        localContainerEntityManagerFactoryBean.setPackagesToScan("com.sandbox_maven.test02.jpa.crud.entities");
//        localContainerEntityManagerFactoryBean.afterPropertiesSet();
//        return localContainerEntityManagerFactoryBean;
//    }
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("com.sandbox_maven.test02.jpa.crud.entities");
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }
}