package com.javatraining.moksiakova.services;

import com.javatraining.moksiakova.CustomResponse;
import com.javatraining.moksiakova.components.CustomerComponent;
import com.javatraining.moksiakova.domain.entity.Customer;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomerService {

    private final CustomerComponent component = new CustomerComponent();

    public CustomResponse<Customer> findCustomer(int customerId) {
        Optional<Customer> customer = component.findById(customerId);
        return customer.map(
                value -> new CustomResponse<>(200, "Ok", value.toString(), value)
        ).orElseGet(
                () -> new CustomResponse<>(404, "Not found", "", null)
        );
    }

    public CustomResponse<Customer> createCustomer(Map params) {
        CustomResponse<Customer> customResponse = this.validateParams(params);
        return customResponse;
    }

    private CustomResponse<Customer> validateParams(Map params) {
        if (!params.containsKey("customer_name")) {
            return new CustomResponse<>(403,"Field customer_name is not set.", "", null);
        }
        if (!params.containsKey("phone")) {
            return new CustomResponse<>(403,"Field phone is not set.","", null);
        }
        return new CustomResponse<>(200, "Ok","", null);
    }
}
