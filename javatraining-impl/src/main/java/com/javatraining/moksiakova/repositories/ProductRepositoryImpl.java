package com.javatraining.moksiakova.repositories;

import com.javatraining.moksiakova.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Objects;

/**
 * class for work with table order in Database.
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductRepositoryImpl {

    private EntityManager entityManager;

    public Product findOrDie(int productId) {
        Product product = entityManager.find(Product.class, productId);
        if (Objects.isNull(product)) {
            throw new EntityNotFoundException(
                    String.format("Can't find Order by ID %d", productId));
        }
        return product;
    }

    public void save(Product product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    public void delete(Product product) {
        entityManager.getTransaction().begin();
        entityManager.remove(product);
        entityManager.getTransaction().commit();
    }

    /**
     * Get collection {@link Product} in database.
     * @return list of Product.
     */
    public List<Product> findAll() {
        List<Product> products = entityManager.createQuery("Select a From Product a", Product.class).getResultList();
        return products;
    }
}
