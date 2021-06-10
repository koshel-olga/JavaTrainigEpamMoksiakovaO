package com.javatraining.moksiakova.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * DataSourceConfig
 *
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource h2TestDataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(h2TestDataSource);
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
        entityManagerFactory.setPersistenceUnitName("javatraining-unit");

        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        propertyMap.put("hibernate.connection.username", "usr");
        propertyMap.put("hibernate.connection.password", "pass");
        propertyMap.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/epamcourses?currentSchema=moksiakova");
        propertyMap.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        propertyMap.put("hibernate.hbm2ddl.auto", "update");
        propertyMap.put("hibernate.show_sql", "false");
        propertyMap.put("hibernate.format_sql", "true");
        entityManagerFactory.setJpaPropertyMap(propertyMap);
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

    @Bean
    public DataSource h2TestDataSource(){
        EmbeddedDatabase build = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
        return build;
    }

}
