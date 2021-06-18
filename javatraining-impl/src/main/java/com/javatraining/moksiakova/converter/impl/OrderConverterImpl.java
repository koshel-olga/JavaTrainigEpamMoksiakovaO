package com.javatraining.moksiakova.converter.impl;

import com.javatraining.moksiakova.converter.OrderConverter;
import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.domain.entity.Order;
import com.javatraining.moksiakova.domain.entity.Product;
import com.javatraining.moksiakova.dto.OrderDTO;
import com.javatraining.moksiakova.repositories.CustomerRepository;
import com.javatraining.moksiakova.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverterImpl implements OrderConverter {

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    @Override
    public OrderDTO convertToDto(Order order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .customerId(order.getCustomer().getCustomerId())
                .orderNumber(order.getOrderNumber())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .products(order.getOrderProducts()).build();
    }

    @Override
    public Order convertToEntity(OrderDTO orderDTO) {
        final Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setOrderDate((Timestamp) orderDTO.getOrderDate());
        order.setOrderNumber(orderDTO.getOrderNumber());
        final Optional<Customer> customer = customerRepository.findById(orderDTO.getCustomerId());
        customer.ifPresent(order::setCustomer);
        order.setTotalAmount(orderDTO.getTotalAmount());
        List<Product> productsEntity = this.getCollectionProducts(orderDTO.getProducts());
        if (!productsEntity.isEmpty()) {
            order.setProducts(productsEntity);
        }
        return order;
    }

    private List<Product> getCollectionProducts(Collection<Integer> products) {
        return products.stream()
                .map(productRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
