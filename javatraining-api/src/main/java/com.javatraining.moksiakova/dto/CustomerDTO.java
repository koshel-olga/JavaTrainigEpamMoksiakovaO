package com.javatraining.moksiakova.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Pojo объект сущности Customer.
 */
@Component
@Data
public class CustomerDTO {

    private int customerId;

    private String customerName;

    private String phone;

    private Set<OrderDTO> customerOrders = new HashSet<>();
}
