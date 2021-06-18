package com.javatraining.moksiakova.resource;


import com.javatraining.moksiakova.dto.CustomerDTO;
import com.javatraining.moksiakova.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerResourceImpl implements CustomerResource {

    private final CustomerService customerService;

    @Override
    public CustomerDTO findCustomer(Integer id) {
        CustomerDTO customer = customerService.findById(id);
        return customer;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customer) {
        CustomerDTO customerDTO = customerService.createCustomer(customer);
        return  customerDTO;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customer) {
        CustomerDTO customerDTO = customerService.updateCustomer(customer);
        return  customerDTO;
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerService.deleteCustomer(id);
    }
}
