package com.javatraining.moksiakova.converter;

import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.dto.CustomerDTO;


public interface CustomerConverter {

    CustomerDTO convertToDto(Customer customer);

    Customer convertToEntity(CustomerDTO customerDTO);
}
