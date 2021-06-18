package com.javatraining.moksiakova.converter;

import com.javatraining.moksiakova.domain.entity.Order;
import com.javatraining.moksiakova.dto.OrderDTO;

public interface OrderConverter {

    OrderDTO convertToDto(Order order);

    Order convertToEntity(OrderDTO orderDTO);
}