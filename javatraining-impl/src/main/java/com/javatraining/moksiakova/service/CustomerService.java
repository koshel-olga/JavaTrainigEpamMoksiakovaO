package com.javatraining.moksiakova.service;

import com.javatraining.moksiakova.dto.CustomerDTO;

public interface CustomerService {

    CustomerDTO findById(Integer customerId);

    /**Collection<CustomerDTO> findAll();

    CustomerDTO createCustomer(Customer customer);

    CustomerDTO updateCustomer(Customer customer);

    CustomerDTO deleteCustomer(int customerId);*/
}
