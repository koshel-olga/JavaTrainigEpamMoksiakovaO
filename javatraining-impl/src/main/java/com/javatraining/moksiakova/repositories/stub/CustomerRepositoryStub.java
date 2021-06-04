package com.javatraining.moksiakova.repositories.stub;

import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CustomerRepositoryStub implements CustomerRepository {

    @Override
    public Customer findOrDie(int customerId) {
        final Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setCustomerName("Stubbing customer name");
        customer.setPhone("12-12-12");
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        final Customer customer = this.findOrDie(89);
        final List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        return customerList;
    }

    @Override
    public void save(Customer customer) {
        customer.setCustomerId(89);
        log.info("Save customer in stub repo.");
    }

    @Override
    public void delete(Customer customer) {
        log.info("Delete customer in stub repo.");
    }
}
