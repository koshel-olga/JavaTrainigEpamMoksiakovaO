package com.javatraining.moksiakova.service;

import com.javatraining.moksiakova.dto.CustomerDTO;

import java.util.Collection;

public interface CustomerService {

    CustomerDTO findById(Integer customerId);

    Collection<CustomerDTO> findAll();

    CustomerDTO createCustomer(CustomerDTO customer);

    CustomerDTO updateCustomer(CustomerDTO customer);

    void deleteCustomer(Integer customerId);
}
