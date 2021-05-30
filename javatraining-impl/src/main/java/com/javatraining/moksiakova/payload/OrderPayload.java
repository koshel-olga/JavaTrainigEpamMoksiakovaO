package com.javatraining.moksiakova.payload;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderPayload {

    private Integer orderId;

    private String orderNumber;

    private Integer customerId;

    private Date orderDate;

    private double totalAmount;

    private Set<Integer> products = new HashSet<>();
}
