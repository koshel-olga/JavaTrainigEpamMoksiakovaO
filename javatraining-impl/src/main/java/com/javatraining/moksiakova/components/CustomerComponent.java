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
     * Create {@link Customer}.
     * @param customerPhone
     * @param customerName
     */
    public Customer createCustomer(String customerPhone, String customerName) {
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
        //} catch (EntityNotFoundException e) {
        //    log.info("Can not update Customer with Id={}",customerId);
        //}
    }

    /**
     * Find {@link Customer} by customer_id.
     * @param customerId
     * @return
     */
    public Optional<Customer> findById(int customerId) {
        try {
            Customer customer = repository.findOrDie(customerId);
            return Optional.of(customer);
        } catch (EntityNotFoundException e) {
            log.info("Entity Customer with Id={} not found",customerId);
        }
        return Optional.empty();
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
    public void delete(int customerId) {
        try {
            Customer customer = repository.findOrDie(customerId);
            repository.delete(customer);
            log.info("Successfully delete customer: {}", customer);
        } catch (EntityNotFoundException e) {
            log.info("Can not delete Customer with Id={}. Entity not exist.",customerId);
        }
    }
}
