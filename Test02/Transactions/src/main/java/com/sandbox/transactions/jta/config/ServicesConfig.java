package com.sandbox.transactions.jta.config;

import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.Properties;


@Configuration
@Slf4j
@EnableTransactionManagement
@ComponentScan(basePackages = "com.sandbox.transactions.jta.services")
public class ServicesConfig {

    @Bean(initMethod = "init", destroyMethod = "shutdownForce")
    public UserTransactionServiceImp userTransactionService(){
        Properties atProps = new Properties();
        atProps.put("com.atomikos.icatch.service",
                "com.atomikos.icatch.standalone.UserTransactionServiceFactory");
        return new UserTransactionServiceImp(atProps);
    }

    @Bean (initMethod = "init", destroyMethod = "close")
    @DependsOn("userTransactionService")
    public UserTransactionManager  atomikosTransactionManager(){
        UserTransactionManager utm = new UserTransactionManager();
        utm.setStartupTransactionService(false);
        utm.setForceShutdown(true);
        return utm;
    }

    @Bean
    @DependsOn("userTransactionService")
    public UserTransaction userTransaction(){
        UserTransactionImp ut = new UserTransactionImp();
        try {
            ut.setTransactionTimeout(300);
        } catch (SystemException se) {
            log.error("Configuration  exception.", se);
            return null;
        }
        return ut;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        JtaTransactionManager ptm = new JtaTransactionManager();
        ptm.setTransactionManager(atomikosTransactionManager());
        ptm.setUserTransaction(userTransaction());
        return ptm;
    }

}