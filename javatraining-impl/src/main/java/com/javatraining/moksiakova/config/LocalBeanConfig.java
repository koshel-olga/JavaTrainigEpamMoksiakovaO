package com.javatraining.moksiakova.config;

import com.javatraining.moksiakova.repositories.CustomerRepository;
import com.javatraining.moksiakova.repositories.stub.CustomerRepositoryStub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile("local")
public class LocalBeanConfig {

    @Bean
    public CustomerRepository beanCustomerRepository() {
        return new CustomerRepositoryStub();
    }
}
