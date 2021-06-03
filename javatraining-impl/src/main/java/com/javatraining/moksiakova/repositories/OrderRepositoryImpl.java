package com.javatraining.moksiakova.repositories;

import com.javatraining.moksiakova.domain.entity.Order;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderRepositoryImpl {

    private EntityManager entityManager;

    public Order findOrDie(int orderId) {
        Order order = entityManager.find(Order.class, orderId);
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
        List<Order> orders = entityManager.createQuery("Select a From Order a", Order.class).getResultList();
        return orders;
    }

    public void save(Order order) {
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
    }

    public void delete(Order order) {
        entityManager.getTransaction().begin();
        entityManager.remove(order);
        entityManager.getTransaction().commit();
    }
}
