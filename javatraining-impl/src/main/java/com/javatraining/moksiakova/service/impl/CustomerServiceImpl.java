package com.javatraining.moksiakova.service.impl;

import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.dto.CustomerDTO;
import com.javatraining.moksiakova.repositories.CustomerRepository;
import com.javatraining.moksiakova.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDTO findById(Integer customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            return customerOptional.get().convertToDTO();
        } else {
            return new CustomerDTO();
        }
    }
}
