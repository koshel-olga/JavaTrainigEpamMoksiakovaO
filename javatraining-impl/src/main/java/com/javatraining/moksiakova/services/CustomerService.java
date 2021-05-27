package com.javatraining.moksiakova.services;

import com.javatraining.moksiakova.CustomResponse;
import com.javatraining.moksiakova.components.CustomerComponent;
import com.javatraining.moksiakova.domain.entity.Customer;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomerService {

    private final CustomerComponent component = new CustomerComponent();

    public Optional<Customer> findCustomer(int customerId) {
        Optional<Customer> customer = component.findById(customerId);
        return customer;
    }

    public CustomResponse createCustomer(Map params) {
        CustomResponse customResponse = this.validateParams(params);
        return customResponse;
    }

    private CustomResponse validateParams(Map params) {
        if (!params.containsKey("customer_name")) {
            return new CustomResponse(403,"Field customer_name is not set.");
        }
        if (!params.containsKey("phone")) {
            return new CustomResponse(403,"Field phone is not set.");
        }
        return new CustomResponse(200, "Ok");
    }
}
