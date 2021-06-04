package com.javatraining.moksiakova.service;

import com.javatraining.moksiakova.CustomResponse;
import com.javatraining.moksiakova.domain.entity.Customer;

import java.util.List;

public interface CustomerService {

    CustomResponse<Customer> findCustomer(int customerId);

    CustomResponse<List<Customer>> findAll();

    CustomResponse<Customer> createCustomer(Customer customer);

    CustomResponse<Customer> updateCustomer(Customer customer);

    CustomResponse<Customer> deleteCustomer(int customerId);
}
