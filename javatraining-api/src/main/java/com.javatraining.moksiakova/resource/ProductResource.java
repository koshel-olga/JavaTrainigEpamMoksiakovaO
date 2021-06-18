package com.javatraining.moksiakova.resource;

import com.javatraining.moksiakova.dto.ProductDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/products")
public interface ProductResource {
    @GetMapping("/{id}")
    ProductDTO findProduct(@PathVariable Integer id);

    @PostMapping
    ProductDTO createProduct(@RequestBody ProductDTO order);

    @PutMapping
    ProductDTO updateProduct(@RequestBody ProductDTO order);

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Integer id);
}
