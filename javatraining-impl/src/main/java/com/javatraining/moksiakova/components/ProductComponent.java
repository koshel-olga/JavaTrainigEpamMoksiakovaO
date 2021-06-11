package com.javatraining.moksiakova.components;

import com.javatraining.moksiakova.domain.entity.Product;

import java.util.List;

public interface ProductComponent {

    Product createProduct(String productName, int supplierId, double unitPrice);

    Product updateProduct(int productId, String productName, double unitPrice, int supplierId);

    Product findById(int productId);

    void deleteProduct(int productId);

    List<Product> findAll();
}
