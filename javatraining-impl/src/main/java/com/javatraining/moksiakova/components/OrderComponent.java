package com.javatraining.moksiakova.components;

import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.domain.entity.Order;
import com.javatraining.moksiakova.payload.OrderPayload;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface OrderComponent {

    /**
     * @param orderPayload
     * @return
     * @throws EntityNotFoundException
     */
    Order createOrder(OrderPayload orderPayload);

    /**
     * @param orderPayload
     * @return
     * @throws EntityNotFoundException
     */
    Order updateOrder(OrderPayload orderPayload) throws EntityNotFoundException;

    /**
     * Find order by order_id.
     * @param orderId
     * @return {@link Order}.
     */
    Order findById(int orderId) throws EntityNotFoundException;

    /**
     * Find all {@link Customer}.
     * @return
     */
    List<Order> findAll();

    /**
     * Delete order by order_id.
     * @param orderId
     */
    void deleteOrder(int orderId) throws EntityNotFoundException;
}
