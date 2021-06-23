package com.javatraining.moksiakova;

import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.dto.CustomerDTO;

import java.util.Random;

public class HardCodeObjects {

    public static CustomerDTO createCustomerDTO() {
        var id = (int) Math.random();
        var customerDto = new CustomerDTO();
        customerDto.setCustomerId(id);
        customerDto.setCustomerName("HardCodeName");
        customerDto.setPhone("123-456-789");
        return customerDto;
    }

    public static Customer createCustomerEntity() {
        var id = (int) Math.random();
        var customer = new Customer();
        customer.setCustomerId(id);
        customer.setCustomerName("HardCodeNameEntity");
        customer.setPhone("987-654-321");
        return customer;
    }
}
