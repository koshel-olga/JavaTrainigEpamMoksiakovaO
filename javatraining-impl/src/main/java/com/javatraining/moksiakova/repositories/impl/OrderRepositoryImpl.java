package com.javatraining.moksiakova.repositories.impl;

import com.javatraining.moksiakova.domain.entity.Order;
import com.javatraining.moksiakova.repositories.OrderRepository;
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
public class OrderRepositoryImpl implements OrderRepository {

    private EntityManager entityManager;

    @Override
    public Order findOrDie(int orderId) {
        Order order = entityManager.find(Order.class, orderId);
        if (Objects.isNull(order)) {
            throw new EntityNotFoundException(
                    String.format("Can't find Order with ID %d", orderId));
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = entityManager.createQuery("Select a From Order a", Order.class).getResultList();
        return orders;
    }

    @Override
    public void save(Order order) {
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Order order) {
        entityManager.getTransaction().begin();
        entityManager.remove(order);
        entityManager.getTransaction().commit();
    }
}
