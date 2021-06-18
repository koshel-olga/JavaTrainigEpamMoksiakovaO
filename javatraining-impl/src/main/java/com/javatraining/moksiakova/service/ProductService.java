package com.javatraining.moksiakova.service;

import com.javatraining.moksiakova.dto.ProductDTO;

public interface ProductService {

    ProductDTO findById(Integer productId);

    //Collection<ProductDTO> findAll();

    ProductDTO createProduct(ProductDTO product);

    ProductDTO updateProduct(ProductDTO product);

    void deleteProduct(Integer productId);
}
