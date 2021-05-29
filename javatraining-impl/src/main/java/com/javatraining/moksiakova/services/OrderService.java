package com.javatraining.moksiakova.services;

import com.javatraining.moksiakova.CustomResponse;
import com.javatraining.moksiakova.components.OrderComponent;
import com.javatraining.moksiakova.domain.entity.Order;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class OrderService {
    private final OrderComponent component = new OrderComponent();

    public CustomResponse<Order> findOrder(int orderId) {
        try {
            Order order = component.findById(orderId);
            return new CustomResponse<>(200, "Ok", order);
        } catch (EntityNotFoundException e) {
            return new CustomResponse<>(400, e.getMessage(), null);
        }
    }

    public CustomResponse<List<Order>> findAll() {
        List<Order> customers = component.findAll();
        return new CustomResponse<>(200, "Ok", customers);
    }

    public CustomResponse<Order> createOrder(Order order) {
        CustomResponse<Order> customResponse = this.validateParams(order);
        if (customResponse.getCode() == 200) {
            try {
                Order newOrder = component.createOrder(
                    order.getOrderNumber(),
                    order.getCustomer().getCustomerId(),
                    order.getTotalAmount()
                );
                customResponse.setEntity(newOrder); }
            catch (EntityNotFoundException e) {
                customResponse.setCode(404);
                customResponse.setMessage(e.getMessage());
            }
        }
        return customResponse;
    }

    public CustomResponse<Order> updateOrder(Order order) {
        CustomResponse<Order> customResponse = this.validateParams(order);
        if (customResponse.getCode() == 200) {
            try {
                Order updateOrder = component.updateOrder(
                        order.getOrderId(),
                        order.getOrderNumber(),
                        order.getTotalAmount(),
                        order.getCustomer().getCustomerId()
                );
                customResponse.setEntity(updateOrder);
            } catch (EntityNotFoundException e) {
                customResponse.setCode(404);
                customResponse.setMessage(e.getMessage());
            }
        }
        return customResponse;
    }

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

    private CustomResponse<Order> validateParams(Order order) {
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
        if (Objects.isNull(order.getCustomer())) {
            code = 400;
            message = "Field customer is not set.";
        }
        return new CustomResponse<>(code, message, null);
    }
}
