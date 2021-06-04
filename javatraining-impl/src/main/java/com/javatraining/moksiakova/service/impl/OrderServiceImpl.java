package com.javatraining.moksiakova.service.impl;

import com.javatraining.moksiakova.CustomResponse;
import com.javatraining.moksiakova.components.impl.OrderComponentImpl;
import com.javatraining.moksiakova.domain.entity.Order;
import com.javatraining.moksiakova.payload.OrderPayload;
import com.javatraining.moksiakova.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {
    private final OrderComponentImpl component;

    public CustomResponse<Order> findOrder(int orderId) {
        try {
            Order order = component.findById(orderId);
            return new CustomResponse<>(200, "Ok", order);
        } catch (EntityNotFoundException e) {
            return new CustomResponse<>(400, e.getMessage(), null);
        }
    }

    @Override
    public CustomResponse<List<Order>> findAll() {
        List<Order> customers = component.findAll();
        return new CustomResponse<>(200, "Ok", customers);
    }

    @Override
    public CustomResponse<Order> createOrder(OrderPayload order) {
        CustomResponse<Order> customResponse = this.validateParams(order);
        if (customResponse.getCode() == 200) {
            try {
                Order newOrder = component.createOrder(order);
                customResponse.setEntity(newOrder); }
            catch (EntityNotFoundException e) {
                customResponse.setCode(404);
                customResponse.setMessage(e.getMessage());
            }
        }
        return customResponse;
    }

    @Override
    public CustomResponse<Order> updateOrder(OrderPayload order) {
        CustomResponse<Order> customResponse = this.validateParams(order);
        if (customResponse.getCode() == 200) {
            try {
                Order updateOrder = component.updateOrder(order);
                customResponse.setEntity(updateOrder);
            } catch (EntityNotFoundException e) {
                customResponse.setCode(404);
                customResponse.setMessage(e.getMessage());
            }
        }
        return customResponse;
    }

    @Override
    public CustomResponse<Order> deleteOrder(int orderId) {
        int code = 200;
        String message = String.format("Successful delete Order with id %d", orderId);
        try {
            component.deleteOrder(orderId);
        } catch (EntityNotFoundException e) {
            code = 404;
            message = e.getMessage();
        }
        return new CustomResponse<>(code,message,null);
    }

    private CustomResponse<Order> validateParams(OrderPayload order) {
        int code = 200;
        String message = "Ok";
        if (Objects.isNull(order.getOrderNumber())) {
            code = 400;
            message = "Field orderNumber is not set.";
        }
        if (Objects.isNull(order.getTotalAmount())) {
            code = 400;
            message = "Field totalAmount is not set.";
        }
        if (Objects.isNull(order.getCustomerId())) {
            code = 400;
            message = "Field customer is not set.";
        }
        return new CustomResponse<>(code, message, null);
    }
}
