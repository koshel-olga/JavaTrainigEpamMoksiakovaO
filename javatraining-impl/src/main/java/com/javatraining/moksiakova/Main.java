package com.javatraining.moksiakova;

import com.javatraining.moksiakova.config.ApplicationConfig;
import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.locale.SetLocale;
import com.javatraining.moksiakova.repositories.CustomerRepository;
import com.javatraining.moksiakova.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Locale;

@Slf4j
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext =  new AnnotationConfigApplicationContext(
                ApplicationConfig.class);

        CustomerRepository customerRepo = appContext.getBean(CustomerRepository.class);
        List<Customer> customers = customerRepo.findAll();
        log.info("Find customer in local profile EN {}",customers);
        SetLocale setLocale = appContext.getBean(SetLocale.class);
        setLocale.setLocale(new Locale("ru"));
        log.info("Find customer in local profile RU {}", customerRepo.findAll());
    }
}
