package com.javatraining.moksiakova.repositories.stub;

import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.locale.SetLocale;
import com.javatraining.moksiakova.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@Profile("local")
@RequiredArgsConstructor
public class CustomerRepositoryStub implements CustomerRepository {

    private final SetLocale locale;

    private final MessageSource messageSource;

    @Override
    public Customer findOrDie(int customerId) {
        final Customer customer = new Customer();
        Integer id;
        try {
            id = Integer.valueOf(
                    messageSource.getMessage("customerId", null, "", locale.getLocale()));
        } catch (Exception e) {
            id = 78;
        }
        String customerName = messageSource.getMessage("customerName",null,"default msg",locale.getLocale());
        String customerPhone = messageSource.getMessage("customerPhone",null,"",locale.getLocale());
        customer.setCustomerId(id);
        customer.setCustomerName(customerName);
        customer.setPhone(customerPhone);
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
