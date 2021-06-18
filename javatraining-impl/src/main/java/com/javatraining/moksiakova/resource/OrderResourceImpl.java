package com.javatraining.moksiakova.resource;

import com.javatraining.moksiakova.dto.CustomerDTO;
import com.javatraining.moksiakova.dto.OrderDTO;
import com.javatraining.moksiakova.repositories.OrderRepository;
import com.javatraining.moksiakova.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderResourceImpl implements OrderResource {

    private final OrderService orderService;

    @Override
    public OrderDTO findOrder(Integer id) {
        OrderDTO order = orderService.findById(id);
        return order;
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return createdOrder;
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        OrderDTO updatedOrder = orderService.updateOrder(orderDTO);
        return  updatedOrder;
    }

    @Override
    public void deleteOrder(Integer id) {
        orderService.deleteOrder(id);
    }
}
