package com.javatraining.moksiakova.service;

import com.javatraining.moksiakova.dto.OrderDTO;

import java.util.Collection;

public interface OrderService {

    OrderDTO findById(Integer orderId);

    Collection<OrderDTO> findAll();

    OrderDTO createOrder(OrderDTO order);

    OrderDTO updateOrder(OrderDTO order);

    void deleteOrder(Integer orderId);
}
