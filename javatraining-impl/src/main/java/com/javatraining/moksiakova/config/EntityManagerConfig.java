package com.javatraining.moksiakova.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@Profile("!local")
public class EntityManagerConfig {

    @Bean
    public EntityManager beanEntityManager() {
        final EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("javatraining-unit");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }
}
