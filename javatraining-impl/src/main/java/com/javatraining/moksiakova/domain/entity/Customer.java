package com.javatraining.moksiakova.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Integer customerId;

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

    @ToString.Exclude
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> customerOrders = new ArrayList<>();

    public Set<Integer> getCustomerOrdersIds() {
        return customerOrders.stream()
                .map(Order::getOrderId)
                .collect(Collectors.toSet());
    }
}
