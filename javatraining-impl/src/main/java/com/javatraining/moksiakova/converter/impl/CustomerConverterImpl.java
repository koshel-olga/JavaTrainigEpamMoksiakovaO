package com.javatraining.moksiakova.converter.impl;

import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.dto.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverterImpl implements CustomerConverter {

    @Override
    public CustomerDTO convertToDto(Customer customer) {
        return CustomerDTO.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .phone(customer.getPhone())
                .customerOrders(customer.getCustomerOrdersIds())
                .build();
    }

    @Override
    public Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setPhone(customerDTO.getPhone());
        return customer;
    }
}
