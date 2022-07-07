package com.sandbox.transactions.jta.config;

import javax.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@Slf4j
@EnableJpaRepositories
public class XAJpaConfig {
    @SuppressWarnings("unchecked")
    @Bean(initMethod = "init", destroyMethod = "close")
    public  AtomikosDataSourceBean dataSourceA() {
        try {
            AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
            dataSource.setUniqueResourceName("XADBMSA");
            dataSource.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
            dataSource.setXaProperties(xaAProperties());
            dataSource.setPoolSize(1);
            return dataSource;
        } catch (Exception e) {
            log.error("Populator DataSource bean cannot be created!", e);
            return null;
        }
    }

    @Bean
    public Properties xaAProperties() {
        Properties xaProp = new Properties();
        xaProp.put("databaseName", "musicdb1");
        xaProp.put("user", "user2");
        xaProp.put("password", "user2");
        return xaProp;
    }

    @SuppressWarnings("unchecked")
    @Bean(initMethod = "init", destroyMethod = "close")
    public AtomikosDataSourceBean dataSourceB() {
        try {
            AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
            dataSource.setUniqueResourceName("XADBMSB");
            dataSource.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
            dataSource.setXaProperties(xaBProperties());
            dataSource.setPoolSize(1);
            return dataSource;
        } catch (Exception e) {
            log.error("Populator DataSource bean cannot be created!", e);
            return null;
        }
    }

    @Bean
    public Properties xaBProperties() {
        Properties xaProp = new Properties();
        xaProp.put("databaseName", "musicdb2");
        xaProp.put("user", "user2");
        xaProp.put("password", "user2");
        return xaProp;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.transaction.factory_class",
                "org.hibernate.transaction.JTATransactionFactory");
        hibernateProp.put(JTA_PLATFORM, "com.atomikos.icatch.jta.hibernate4.AtomikosPlatform");
        hibernateProp.put(TRANSACTION_COORDINATOR_STRATEGY, "jta");
        hibernateProp.put(CURRENT_SESSION_CONTEXT_CLASS, "jta");
        hibernateProp.put(AUTOCOMMIT, false);
        hibernateProp.put(FLUSH_BEFORE_COMPLETION, false);
        hibernateProp.put(DIALECT, "org.hibernate.dialect.MySQL57Dialect");
        hibernateProp.put(HBM2DDL_AUTO, "create-drop");
        hibernateProp.put(SHOW_SQL, true);
        hibernateProp.put(MAX_FETCH_DEPTH, 3);
        hibernateProp.put(STATEMENT_BATCH_SIZE, 10);
        hibernateProp.put(STATEMENT_FETCH_SIZE, 50);
        return hibernateProp;
    }


    @Bean
    public EntityManagerFactory emfA() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("com.sandbox.transactions.jta.entities");
        factoryBean.setDataSource(dataSourceA());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setPersistenceUnitName("emfA");
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }


    @Bean
    public EntityManagerFactory emfB() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("com.sandbox.transactions.jta.entities");
        factoryBean.setDataSource(dataSourceB());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setPersistenceUnitName("emfB");
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }
}