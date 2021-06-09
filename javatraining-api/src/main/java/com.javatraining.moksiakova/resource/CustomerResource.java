package com.javatraining.moksiakova.resource;

import com.javatraining.moksiakova.dto.CustomerDTO;
import org.springframework.web.bind.annotation.*;

@RestController("/customers")
public interface CustomerResource {

    @GetMapping("/{id}")
    CustomerDTO findCustomer(@PathVariable Integer id);

    @PostMapping
    String createCustomer(@RequestBody CustomerDTO customer);

    @PutMapping
    String updateCustomer(@RequestBody CustomerDTO customer);

    @DeleteMapping("/{id}")
    String deleteCustomer(@PathVariable Integer id);
}
