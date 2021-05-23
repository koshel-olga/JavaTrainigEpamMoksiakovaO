package com.javatraining.moksiakova.components;

import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.domain.entity.Order;
import com.javatraining.moksiakova.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
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
    public void createOrder(String orderNumber,
                            int customerId,
                            double totalAmount) {
        Optional<Customer> customerExist
                = this.customerComponent.findById(customerId);
        if (customerExist.isPresent()) {
            Order order = new Order();
            order.setOrderNumber(orderNumber);
            order.setCustomer(customerExist.get());
            order.setOrderDate(new Timestamp(System.currentTimeMillis()));
            order.setTotalAmount(totalAmount);
            repository.save(order);
            log.info("Successfully create Order: {}", order);
        } else {
            log.info("Can not create Order with Customer with id {}",customerId);
        }
    }

    /**
     * Update order by order_id.
     * @param orderId
     * @param orderNumber
     * @param totalAmount
     */
    public void updateOrder(int orderId,
                            String orderNumber,
                            double totalAmount)
    {
        Optional<Order> orderOptional = this.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setOrderNumber(orderNumber);
            order.setTotalAmount(totalAmount);
            repository.save(order);
            log.info("Successfully update Order: {}", order);
        } else {
            log.info("Can not update Order with Id={}",orderId);
        }
    }

    /**
     * Find order by order_id.
     * @param orderId
     * @return {@link Order}.
     */
    public Optional<Order> findById(int orderId) {
        try {
            Order order = repository.findOrDie(orderId);
            return Optional.of(order);
        } catch (EntityNotFoundException e) {
            log.info("Entity Order with Id={} not found",orderId);
        }
        return Optional.empty();
    }

    /**
     * Delete order by order_id.
     * @param orderId
     */
    public void deleteOrder(int orderId) {
        Optional<Order> orderOptional = this.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            repository.delete(order);
            log.info("Successfully delete Order: {}", order);
        } else {
            log.info("Can not delete Order with Id={}. Entity not exist.",orderId);
        }
    }
}
