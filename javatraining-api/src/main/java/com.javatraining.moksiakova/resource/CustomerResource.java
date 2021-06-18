package com.javatraining.moksiakova.resource;

import com.javatraining.moksiakova.dto.CustomerDTO;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/customers")
public interface CustomerResource {

    @GetMapping("/{id}")
    CustomerDTO findCustomer(@PathVariable Integer id);

    @PostMapping
    CustomerDTO createCustomer(@RequestBody CustomerDTO customer);

    @PutMapping
    CustomerDTO updateCustomer(@RequestBody CustomerDTO customer);

    @DeleteMapping("/{id}")
    void deleteCustomer(@PathVariable Integer id);
}
