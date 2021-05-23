package com.javatraining.moksiakova.repositories;

import com.javatraining.moksiakova.domain.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.util.Objects;

/**
 * class for work with table order in Database.
 */
public class OrderRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("javatraining-unit");
    private EntityManager em = emf.createEntityManager();

    public Order findOrDie(int orderId) {
        Order order = em.find(Order.class, orderId);
        if (Objects.isNull(order)) {
            throw new EntityNotFoundException(
                    String.format("Can't find Order by ID %d", orderId));
        }
        return order;
    }

    public void save(Order order) {
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
    }

    public void delete(Order order) {
        em.getTransaction().begin();
        em.remove(order);
        em.getTransaction().commit();
    }
}
