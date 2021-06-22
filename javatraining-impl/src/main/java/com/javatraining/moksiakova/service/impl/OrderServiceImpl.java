package com.javatraining.moksiakova.service.impl;

import com.javatraining.moksiakova.converter.OrderConverter;
import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.domain.entity.Order;
import com.javatraining.moksiakova.dto.CustomerDTO;
import com.javatraining.moksiakova.dto.OrderDTO;
import com.javatraining.moksiakova.repositories.OrderRepository;
import com.javatraining.moksiakova.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderConverter orderConverter;

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public OrderDTO findById(Integer orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            return orderConverter.convertToDto(orderOptional.get());
        }
        throw new EntityNotFoundException(String.format("Not found Order with Id: %d", orderId));
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public Collection<OrderDTO> findAll() {
        Collection<Order> orders = orderRepository.findAll();
        Collection<OrderDTO> ordersDTO = orders.stream()
                .map(orderConverter::convertToDto)
                .collect(Collectors.toSet());
        return ordersDTO;
    }

    @Override
    public OrderDTO createOrder(OrderDTO order) {
        Order orderEntity = orderConverter.convertToEntity(order);
        orderEntity.setOrderId(null);
        orderRepository.save(orderEntity);
        return orderConverter.convertToDto(orderEntity);
    }

    @Override
    public OrderDTO updateOrder(OrderDTO order) {
        Order orderEntity = orderConverter.convertToEntity(order);
        orderRepository.save(orderEntity);
        return orderConverter.convertToDto(orderEntity);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }
}
