package com.javatraining.moksiakova;

import com.javatraining.moksiakova.components.CustomerComponent;
import com.javatraining.moksiakova.domain.entity.Customer;

import java.util.Optional;

public class MainImpl {
    public static void main(String[] args) {
        CustomerComponent customerComponent = new CustomerComponent();
        customerComponent.createCustomer("123456","Telegraph");
        customerComponent.updateCustomer(2,"98765","updateCustomerName");
        Optional<Customer> customer = customerComponent.findById(2);
        customerComponent.delete(2);
        Optional<Customer> customerRemoved = customerComponent.findById(3);

    }
}
