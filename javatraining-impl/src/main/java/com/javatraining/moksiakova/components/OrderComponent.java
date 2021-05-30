package com.javatraining.moksiakova.components;

import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.domain.entity.Order;
import com.javatraining.moksiakova.domain.entity.Product;
import com.javatraining.moksiakova.payload.OrderPayload;
import com.javatraining.moksiakova.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Component  for work with {@link Order}.
 */
@Slf4j
public class OrderComponent {

    private final OrderRepository repository;

    private final CustomerComponent customerComponent;

    private final ProductComponent productComponent;

    public OrderComponent() {
        this.repository = new OrderRepository();
        this.customerComponent = new CustomerComponent();
        this.productComponent = new ProductComponent();
    }

    /**
     *
     * @param orderPayload
     * @return
     * @throws EntityNotFoundException
     */
    public Order createOrder(OrderPayload orderPayload)
            throws EntityNotFoundException
    {
        Customer customerExist
                = this.customerComponent.findById(orderPayload.getCustomerId());
        Order order = new Order();
        order.setOrderNumber(orderPayload.getOrderNumber());
        order.setCustomer(customerExist);
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalAmount(orderPayload.getTotalAmount());
        List<Product> products = new ArrayList<>();
        orderPayload.getProducts().forEach( productId -> {
                Product product = productComponent.findById(productId);
                products.add(product);
            }
        );
        order.setProducts(products);
        repository.save(order);
        log.info("Successfully create Order: {}", order);
        return order;
    }

    /**
     *
     * @param orderPayload
     * @return
     * @throws EntityNotFoundException
     */
    public Order updateOrder(OrderPayload orderPayload)
    throws EntityNotFoundException
    {
        Customer customerExist
                = this.customerComponent.findById(orderPayload.getCustomerId());
        Order order = this.findById(orderPayload.getOrderId());
        order.setOrderNumber(orderPayload.getOrderNumber());
        order.setTotalAmount(orderPayload.getTotalAmount());
        order.setCustomer(customerExist);
        order.setProducts(this.addProducts(orderPayload));
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

    private List<Product> addProducts(OrderPayload  orderPayload) {
        List<Product> products = new ArrayList<>();
        orderPayload.getProducts().forEach( productId -> {
                    Product product = productComponent.findById(productId);
                    products.add(product);
                }
        );
        return products;
    }
}
