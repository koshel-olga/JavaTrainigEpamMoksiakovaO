package com.javatraining.moksiakova.service.impl;

import com.javatraining.moksiakova.converter.CustomerConverter;
import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.dto.CustomerDTO;
import com.javatraining.moksiakova.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerConverter customerConverter;

    @InjectMocks
    private CustomerServiceImpl service;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findById() {
        var id = 1;
        var customer = new Customer();
        customer.setCustomerId(id);
        var expectedResult = new CustomerDTO();
        expectedResult.setCustomerId(id);

        when(this.customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(this.customerConverter.convertToDto(customer)).thenReturn(expectedResult);

        var actualResult = this.service.findById(id);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void findByIdThrowException() {
        var id = 1;
        when(this.customerRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> service.findById(id));
        assertEquals("Not found Customer with Id: 1", exception.getMessage());
    }

    @Test
    void findAll() {

    }

    @Test
    void createCustomer() {
        var id = 21;
        var customerDto = new CustomerDTO();
        customerDto.setCustomerId(id);
        customerDto.setCustomerName("test");
        var customerEntity = new Customer();
        customerEntity.setCustomerId(customerDto.getCustomerId());
        customerEntity.setCustomerName(customerDto.getCustomerName());

        when(this.customerConverter.convertToEntity(customerDto)).thenReturn(customerEntity);
        when(this.customerRepository.save(customerEntity)).thenReturn(customerEntity);
        when(this.customerConverter.convertToDto(customerEntity)).thenReturn(customerDto);

        var actualResult = this.service.createCustomer(customerDto);
        assertEquals(actualResult, customerEntity);
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}