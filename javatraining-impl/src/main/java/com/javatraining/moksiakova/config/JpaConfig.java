package com.javatraining.moksiakova.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * JpaConfig
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = {
        com.javatraining.moksiakova.repositories.CustomerRepository.class
})
public class JpaConfig {
}
