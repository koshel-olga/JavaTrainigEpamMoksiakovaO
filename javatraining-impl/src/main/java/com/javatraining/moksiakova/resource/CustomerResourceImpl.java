package com.javatraining.moksiakova.resource;


import com.javatraining.moksiakova.dto.CustomerDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerResourceImpl implements CustomerResource {
    @Override
    public CustomerDTO findCustomer(Integer id) {
        return null;
    }

    @Override
    public String createCustomer(CustomerDTO customer) {
        return null;
    }

    @Override
    public String updateCustomer(CustomerDTO customer) {
        return null;
    }

    @Override
    public String deleteCustomer(Integer id) {
        return null;
    }
}
