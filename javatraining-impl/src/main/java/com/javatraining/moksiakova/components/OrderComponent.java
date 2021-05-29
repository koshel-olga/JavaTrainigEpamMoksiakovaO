package com.javatraining.moksiakova.components;

import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.domain.entity.Order;
import com.javatraining.moksiakova.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * Component  for work with {@link Order}.
 */
@Slf4j
public class OrderComponent {

    private OrderRepository repository;

    private CustomerComponent customerComponent;

    public OrderComponent() {
        this.repository = new OrderRepository();
        this.customerComponent = new CustomerComponent();
    }

    /**
     * Create order.
     * @param orderNumber
     * @param customerId
     * @param totalAmount
     */
    public Order createOrder(String orderNumber,
                            int customerId,
                            double totalAmount)
            throws EntityNotFoundException
    {
        Customer customerExist
                = this.customerComponent.findById(customerId);
        Order order = new Order();
        order.setOrderNumber(orderNumber);
        order.setCustomer(customerExist);
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalAmount(totalAmount);
        repository.save(order);
        log.info("Successfully create Order: {}", order);
        return order;
    }

    /**
     * Update order by order_id.
     * @param orderId
     * @param orderNumber
     * @param totalAmount
     */
    public Order updateOrder(int orderId,
                            String orderNumber,
                            double totalAmount,
                             int customerId)
    throws EntityNotFoundException
    {
        Customer customerExist
                = this.customerComponent.findById(customerId);
        Order order = this.findById(orderId);
        order.setOrderNumber(orderNumber);
        order.setTotalAmount(totalAmount);
        order.setCustomer(customerExist);
        repository.save(order);
        log.info("Successfully update Order: {}", order);
        return order;
    }

    /**
     * Find order by order_id.
     * @param orderId
     * @return {@link Order}.
     */
    public Order findById(int orderId) throws EntityNotFoundException {
        Order order = repository.findOrDie(orderId);
        return order;
    }

    /**
     * Find all {@link Customer}.
     * @return
     */
    public List<Order> findAll() {
        return repository.findAll();
    }

    /**
     * Delete order by order_id.
     * @param orderId
     */
    public void deleteOrder(int orderId) throws EntityNotFoundException {
        Order order = this.findById(orderId);
        repository.delete(order);
        log.info("Successfully delete Order: {}", order);
    }
}
