package com.javatraining.moksiakova.service;

import com.javatraining.moksiakova.CustomResponse;
import com.javatraining.moksiakova.domain.entity.Product;

import java.util.List;

public interface ProductService {

    CustomResponse<Product> findProduct(int productId);

    CustomResponse<List<Product>> findAll();

    CustomResponse<Product> createProduct(Product product);

    CustomResponse<Product> updateProduct(Product product);

    CustomResponse<Product> deleteProduct(int productId);
}
