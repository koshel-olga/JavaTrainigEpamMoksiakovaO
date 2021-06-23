package com.javatraining.moksiakova.converter.impl;

import com.javatraining.moksiakova.HardCodeObjects;
import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.dto.CustomerDTO;
import com.javatraining.moksiakova.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CustomerConverterImplTest {
    @InjectMocks
    private CustomerConverterImpl converter;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void convertToDto() {
        Customer customerEntity = HardCodeObjects.createCustomerEntity();
        var actualResult = converter.convertToDto(customerEntity);

        assertEquals(customerEntity.getCustomerId(), actualResult.getCustomerId());
        assertEquals(customerEntity.getCustomerName(), actualResult.getCustomerName());
        assertEquals(customerEntity.getPhone(), actualResult.getPhone());
    }

    @Test
    void convertToEntity() {
        CustomerDTO customerDTO = HardCodeObjects.createCustomerDTO();
        var actualResult = converter.convertToEntity(customerDTO);

        assertEquals(customerDTO.getCustomerId(), actualResult.getCustomerId());
        assertEquals(customerDTO.getCustomerName(), actualResult.getCustomerName());
        assertEquals(customerDTO.getPhone(), actualResult.getPhone());
    }
}