package com.javatraining.moksiakova.service.impl;

import com.javatraining.moksiakova.HardCodeObjects;
import com.javatraining.moksiakova.converter.CustomerConverter;
import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.dto.CustomerDTO;
import com.javatraining.moksiakova.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
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
        var customer = HardCodeObjects.createCustomerEntity();
        var expectedResult = HardCodeObjects.createCustomerDTO();

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
    void createCustomer() {
        var customerDto = HardCodeObjects.createCustomerDTO();
        var customerEntityWithoutId = HardCodeObjects.createCustomerEntity();
        customerEntityWithoutId.setCustomerId(null);

        when(this.customerConverter.convertToEntity(customerDto)).thenReturn(customerEntityWithoutId);
        when(this.customerConverter.convertToDto(customerEntityWithoutId)).thenReturn(customerDto);

        var actualResult = this.service.createCustomer(customerDto);
        assertEquals(customerDto, actualResult);

        verify(customerRepository).save(customerEntityWithoutId);
    }

    @Test
    void updateCustomer() {
        var customerDto = HardCodeObjects.createCustomerDTO();
        var customerEntity = HardCodeObjects.createCustomerEntity();

        when(this.customerConverter.convertToEntity(customerDto)).thenReturn(customerEntity);
        when(this.customerConverter.convertToDto(customerEntity)).thenReturn(customerDto);

        var actualResult = this.service.createCustomer(customerDto);
        assertEquals(customerDto, actualResult);

        verify(customerRepository).save(customerEntity);
    }

    @Test
    void deleteCustomer() {
        var id = 3;
        this.service.deleteCustomer(id);

        verify(customerRepository).deleteById(id);
    }
}