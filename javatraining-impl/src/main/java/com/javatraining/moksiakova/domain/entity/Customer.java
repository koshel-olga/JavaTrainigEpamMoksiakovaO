package com.javatraining.moksiakova.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity class for table customer.
 */
@Entity
@Table(name="customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
