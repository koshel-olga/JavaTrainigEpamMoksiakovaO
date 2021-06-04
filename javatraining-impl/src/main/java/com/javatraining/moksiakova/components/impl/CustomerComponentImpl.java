package com.javatraining.moksiakova.components.impl;

import com.javatraining.moksiakova.components.CustomerComponent;
import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.repositories.impl.CustomerRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Component for work with {@link Customer}.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerComponentImpl implements CustomerComponent {

    /**
     * Component for work with table in database.
     */
    private CustomerRepositoryImpl repository;

    @Override
    public Customer createCustomer(String customerName, String customerPhone) {
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setPhone(customerPhone);
        repository.save(customer);
        log.info("Successfully create Customer: {}",customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(int customerId, String customerName, String customerPhone) {
        Customer customer = repository.findOrDie(customerId);
        customer.setCustomerName(customerName);
        customer.setPhone(customerPhone);
        repository.save(customer);
        log.info("Successfully update Customer: {}", customer);
        return customer;
    }

    @Override
    public Customer findById(int customerId) throws EntityNotFoundException
    {
        Customer customer = repository.findOrDie(customerId);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteCustomer(int customerId) {
        Customer customer = repository.findOrDie(customerId);
        repository.delete(customer);
        log.info("Successfully delete customer: {}", customer);
    }
}
