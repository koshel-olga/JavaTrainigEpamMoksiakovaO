package com.javatraining.moksiakova.repositories;

import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.domain.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.util.List;
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
                    String.format("Can't find Order with ID %d", orderId));
        }
        return order;
    }

    /**
     * Get collection {@link Order} in database.
     * @return list of orders.
     */
    public List<Order> findAll() {
        List<Order> orders = em.createQuery("Select a From Order a", Order.class).getResultList();
        return orders;
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
