package com.javatraining.moksiakova.service;

import com.javatraining.moksiakova.CustomResponse;
import com.javatraining.moksiakova.domain.entity.Order;
import com.javatraining.moksiakova.payload.OrderPayload;

import java.util.List;

public interface OrderService {
    CustomResponse<Order> findOrder(int orderId);

    CustomResponse<List<Order>> findAll();

    CustomResponse<Order> createOrder(OrderPayload order);

    CustomResponse<Order> updateOrder(OrderPayload order);

    CustomResponse<Order> deleteOrder(int orderId);
}
