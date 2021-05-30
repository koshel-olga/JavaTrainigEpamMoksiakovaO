package com.javatraining.moksiakova.components;

import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * Component for work with {@link Customer}.
 */
@Slf4j
public class CustomerComponent {

    /**
     * Component for work with table in database.
     */
    private CustomerRepository repository;

    public CustomerComponent() {
        this.repository = new CustomerRepository();
    }

    /**
     *
     * @param customerName
     * @param customerPhone
     * @return
     */
    public Customer createCustomer(String customerName, String customerPhone) {
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setPhone(customerPhone);
        repository.save(customer);
        log.info("Successfully create Customer: {}",customer);
        return customer;
    }

    /**
     * Update {@link Customer} by customer_id.
     * @param customerId
     * @param customerPhone
     * @param customerName
     */
    public Customer updateCustomer(int customerId, String customerName, String customerPhone) {
        Customer customer = repository.findOrDie(customerId);
        customer.setCustomerName(customerName);
        customer.setPhone(customerPhone);
        repository.save(customer);
        log.info("Successfully update Customer: {}", customer);
        return customer;
    }

    /**
     * Find {@link Customer} by customer_id.
     * @param customerId
     * @return
     */
    public Customer findById(int customerId) throws EntityNotFoundException
    {
        Customer customer = repository.findOrDie(customerId);
        return customer;
    }

    /**
     * Find all {@link Customer}.
     * @return
     */
    public List<Customer> findAll() {
        return repository.findAll();
    }

    /**
     * Delete {@link Customer} by customer_id.
     * @param customerId
     */
    public void deleteCustomer(int customerId) {
        Customer customer = repository.findOrDie(customerId);
        repository.delete(customer);
        log.info("Successfully delete customer: {}", customer);
    }
}
