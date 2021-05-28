package com.javatraining.moksiakova.services;

import com.javatraining.moksiakova.CustomResponse;
import com.javatraining.moksiakova.components.CustomerComponent;
import com.javatraining.moksiakova.domain.entity.Customer;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomerService {

    private final CustomerComponent component = new CustomerComponent();

    public CustomResponse<Customer> findCustomer(int customerId) {
        Optional<Customer> customer = component.findById(customerId);
        return customer.map(value -> new CustomResponse<>(200, "Ok", value))
                .orElseGet(() -> new CustomResponse<>(404, "Not found", null));
    }

    public CustomResponse<List<Customer>> findAll() {
        List<Customer> customers = component.findAll();
        return new CustomResponse<>(200, "Ok", customers);
    }

    public CustomResponse<Customer> createCustomer(Customer customer) {
        CustomResponse<Customer> customResponse = this.validateParams(customer);
        if (customResponse.getCode() == 200) {
            Customer newCustomer = component.createCustomer(customer.getCustomerName(),
                                                            customer.getPhone());
            customResponse.setEntity(newCustomer);
        }
        return customResponse;
    }

    public CustomResponse<Customer> updateCustomer(Customer customer) {
        CustomResponse<Customer> customResponse = this.validateParams(customer);
        if (customResponse.getCode() == 200) {
            try {
                Customer updateCustomer = component.updateCustomer(customer.getCustomerId(), customer.getCustomerName(),
                        customer.getPhone());
                customResponse.setEntity(updateCustomer);
            } catch (EntityNotFoundException e) {
                customResponse.setCode(404);
                customResponse.setMessage(e.getMessage());
            }
        }
        return customResponse;
    }

    private CustomResponse<Customer> validateParams(Customer customer) {
        if (Objects.isNull(customer.getCustomerName())) {
            return new CustomResponse<>(404,"Field customerName is not set.", null);
        }
        if (Objects.isNull(customer.getPhone())) {
            return new CustomResponse<>(404,"Field phone is not set.", null);
        }
        return new CustomResponse<>(200, "Ok", null);
    }
}
