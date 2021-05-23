package com.javatraining.moksiakova.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for table customer.
 */
@Entity
@Table(name="customer")
@Data
@NoArgsConstructor
public class Customer {

    /**
     * Identification customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id", columnDefinition = "serial")
    private int customerId;

    /**
     * Name of customer.
     */
    @Column(name="customer_name")
    private String customerName;

    /**
     * Phone of customer.
     */
    @Column(name="phone")
    private String phone;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> customerOrders = new ArrayList<>();
}
