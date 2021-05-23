package com.javatraining.moksiakova.repositories;

import com.javatraining.moksiakova.domain.entity.Order;
import com.javatraining.moksiakova.domain.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.util.Objects;

/**
 * class for work with table order in Database.
 */
public class ProductRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("javatraining-unit");
    private EntityManager em = emf.createEntityManager();

    public Product findOrDie(int productId) {
        Product product = em.find(Product.class, productId);
        if (Objects.isNull(product)) {
            throw new EntityNotFoundException(
                    String.format("Can't find Order by ID %d", productId));
        }
        return product;
    }

    public void save(Product product) {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    public void delete(Product product) {
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
    }
}
