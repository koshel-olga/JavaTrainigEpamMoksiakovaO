package com.javatraining.moksiakova.service.impl;

import com.javatraining.moksiakova.converter.impl.CustomerConverter;
import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.dto.CustomerDTO;
import com.javatraining.moksiakova.repositories.CustomerRepository;
import com.javatraining.moksiakova.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerConverter customerConverter;

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public CustomerDTO findById(Integer customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            return customerConverter.convertToDto(customerOptional.get());
        }
        throw new EntityNotFoundException(String.format("Not found Customer with Id: %d",customerId));
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public Collection<CustomerDTO> findAll() {
        Collection<Customer> customers = customerRepository.findAll();
        Collection<CustomerDTO> customersDTO = customers.stream()
                .map(customerConverter::convertToDto)
                .collect(Collectors.toSet());
        return customersDTO;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customer) {
        Customer customerEntity = customerConverter.convertToEntity(customer);
        customerEntity.setCustomerId(null);
        customerRepository.save(customerEntity);
        return customerConverter.convertToDto(customerEntity);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customerEntity = customerConverter.convertToEntity(customerDTO);
        customerRepository.save(customerEntity);
        return customerConverter.convertToDto(customerEntity);
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        customerRepository.deleteById(customerId);
    }
}
