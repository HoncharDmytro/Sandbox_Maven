package com.sandbox.testing.jupiter.config;

import com.sandbox.remoting.boot_rest.services.DbInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages={"com.sandbox.testing.jupiter"},
        excludeFilters =  {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                value = DbInitializer.class)
        })
@Profile("test")
public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    @Bean
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder.setType(EmbeddedDatabaseType.H2).build();
        } catch (Exception e) {
            logger.error("Embedded DataSource bean cannot be created!", e);
            return null;
        }
    }

}