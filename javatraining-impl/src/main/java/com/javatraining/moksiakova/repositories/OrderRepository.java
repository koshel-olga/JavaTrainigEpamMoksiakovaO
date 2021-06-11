package com.javatraining.moksiakova.repositories;

import com.javatraining.moksiakova.domain.entity.Order;

import java.util.List;

public interface OrderRepository {

    Order findOrDie(int orderId);

    /**
     * Get collection {@link Order} in database.
     * @return list of orders.
     */
    List<Order> findAll();

    void save(Order order);

    void delete(Order order);
}
