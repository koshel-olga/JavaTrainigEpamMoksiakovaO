package com.javatraining.moksiakova.resource;

import com.javatraining.moksiakova.dto.CustomerDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RequestMapping(value = "/customers")
public interface CustomerResource {

    @GetMapping(value = "/{id}", produces = "application/json")
    CustomerDTO findCustomer(@PathVariable Integer id);

    @GetMapping
    Collection<CustomerDTO> findAllCustomer();

    @PostMapping
    CustomerDTO createCustomer(@RequestBody CustomerDTO customer);

    @PutMapping
    CustomerDTO updateCustomer(@RequestBody CustomerDTO customer);

    @DeleteMapping("/{id}")
    void deleteCustomer(@PathVariable Integer id);
}
