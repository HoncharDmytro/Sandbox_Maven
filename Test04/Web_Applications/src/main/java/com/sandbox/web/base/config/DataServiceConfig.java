//package com.sandbox.web.base.config;
//
//import javax.persistence.EntityManagerFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.Properties;
//
///**
// * Created by Dmytro Honchar
// * Date: 7/12/2022
// */
//
//@Configuration
//@EnableJpaRepositories(basePackages = {"com.sandbox.web.base.repositories"})
//@ComponentScan(basePackages  = {"com.sandbox.web.base"})
//@PropertySource("classpath:properties/app.properties")
//@Slf4j
//public class DataServiceConfig {
//    @Value("${driverClassName}")
//    private String driverClassName;
//    @Value("${url}")
//    private String url;
//    @Value("${user}")
//    private String username;
//    @Value("${password}")
//    private String password;
//    @Value("${dialect}")
//    private String dialect;
//
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
//
//    @Bean
//    public BasicDataSource dataSource() {
//        try {
//            //DriverManagerDataSource dataSource = new DriverManagerDataSource();
//            BasicDataSource dataSource = new BasicDataSource();
//            dataSource.setDriverClassName(driverClassName);
//            dataSource.setUrl(url);
//            dataSource.setUsername(username);
//            dataSource.setPassword(password);
//            return dataSource;
//        } catch (Exception e) {
//            logger.error("BasicDataSource bean cannot be created!", e);
//            return null;
//        }
//    }
//
//    @Bean
//    public Properties hibernateProperties() {
//        Properties hibernateProp = new Properties();
//        hibernateProp.put("hibernate.dialect", dialect);
//        hibernateProp.put("hibernate.hbm2ddl.auto", "create-drop");
//        hibernateProp.put("hibernate.show_sql", true);
//        hibernateProp.put("hibernate.max_fetch_depth", 3);
//        hibernateProp.put("hibernate.jdbc.batch_size", 10);
//        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
//        return hibernateProp;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new JpaTransactionManager(entityManagerFactory());
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        return new HibernateJpaVendorAdapter();
//    }
//
//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setPackagesToScan("com.sandbox.web.base.entities");
//        factoryBean.setDataSource(dataSource());
//        factoryBean.setJpaProperties(hibernateProperties());
//        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
//        factoryBean.afterPropertiesSet();
//        return factoryBean.getNativeEntityManagerFactory();
//    }
//}