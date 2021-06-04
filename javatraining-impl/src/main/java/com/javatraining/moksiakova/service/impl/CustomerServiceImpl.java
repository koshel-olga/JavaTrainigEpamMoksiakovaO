package com.javatraining.moksiakova.service.impl;

import com.javatraining.moksiakova.CustomResponse;
import com.javatraining.moksiakova.components.impl.CustomerComponentImpl;
import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceImpl implements CustomerService {

    private final CustomerComponentImpl component;

    @Override
    public CustomResponse<Customer> findCustomer(int customerId) {
        try {
            Customer customer = component.findById(customerId);
            return new CustomResponse<>(200, "Ok", customer);
        } catch (EntityNotFoundException e) {
            return new CustomResponse<>(400, e.getMessage(), null);
        }
    }

    @Override
    public CustomResponse<List<Customer>> findAll() {
        List<Customer> customers = component.findAll();
        return new CustomResponse<>(200, "Ok", customers);
    }

    @Override
    public CustomResponse<Customer> createCustomer(Customer customer) {
        CustomResponse<Customer> customResponse = this.validateParams(customer);
        if (customResponse.getCode() == 200) {
            Customer newCustomer = component.createCustomer(customer.getCustomerName(),
                                                            customer.getPhone());
            customResponse.setEntity(newCustomer);
        }
        return customResponse;
    }

    @Override
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

    @Override
    public CustomResponse<Customer> deleteCustomer(int customerId) {
        int code = 200;
        String message = String.format("Successful delete Customer with id %d", customerId);
        try {
            component.deleteCustomer(customerId);
        } catch (EntityNotFoundException e) {
            code = 404;
            message = e.getMessage();
        }
        return new CustomResponse<>(code,message,null);
    }

    private CustomResponse<Customer> validateParams(Customer customer) {
        int code = 200;
        String message = "Ok";
        if (Objects.isNull(customer.getCustomerName())) {
            code = 400;
            message = "Field customerName is not set.";
        }
        if (Objects.isNull(customer.getPhone())) {
            code = 400;
            message = "Field phone is not set.";
        }
        return new CustomResponse<>(code, message, null);
    }
}
