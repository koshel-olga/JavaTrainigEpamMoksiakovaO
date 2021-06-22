package com.javatraining.moksiakova.resource;

import com.javatraining.moksiakova.dto.CustomerDTO;
import com.javatraining.moksiakova.dto.SupplierDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RequestMapping(value = "/suppliers")
public interface SupplierResource {
    @GetMapping("/{id}")
    SupplierDTO findSupplier(@PathVariable Integer id);

    @GetMapping
    Collection<SupplierDTO> findAllSupplier();

    @PostMapping
    SupplierDTO createSupplier(@RequestBody SupplierDTO order);

    @PutMapping
    SupplierDTO updateSupplier(@RequestBody SupplierDTO order);

    @DeleteMapping("/{id}")
    void deleteSupplier(@PathVariable Integer id);
}
