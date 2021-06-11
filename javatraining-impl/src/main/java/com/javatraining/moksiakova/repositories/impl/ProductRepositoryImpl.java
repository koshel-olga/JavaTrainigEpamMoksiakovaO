package com.javatraining.moksiakova.repositories.impl;

import com.javatraining.moksiakova.domain.entity.Product;
import com.javatraining.moksiakova.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

/**
 * class for work with table order in Database.
 */
@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private EntityManager entityManager;

    @Override
    public Product findOrDie(int productId) {
        Product product = entityManager.find(Product.class, productId);
        if (Objects.isNull(product)) {
            throw new EntityNotFoundException(
                    String.format("Can't find Order by ID %d", productId));
        }
        return product;
    }

    @Override
    public void save(Product product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Product product) {
        entityManager.getTransaction().begin();
        entityManager.remove(product);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = entityManager.createQuery("Select a From Product a", Product.class).getResultList();
        return products;
    }
}
