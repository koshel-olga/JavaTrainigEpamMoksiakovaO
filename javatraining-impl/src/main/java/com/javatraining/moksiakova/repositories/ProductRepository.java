package com.javatraining.moksiakova.repositories;

import com.javatraining.moksiakova.domain.entity.Product;

import java.util.List;

public interface ProductRepository {

    Product findOrDie(int productId);

    void save(Product product);

    void delete(Product product);

    /**
     * Get collection {@link Product} in database.
     * @return list of Product.
     */
    List<Product> findAll();
}
