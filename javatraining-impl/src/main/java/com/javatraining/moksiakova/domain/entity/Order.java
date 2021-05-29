package com.javatraining.moksiakova.domain.entity;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for table order.
 */
@Entity
@Table(name="order",schema = "moksiakova")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    /**
     * Identification order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id", columnDefinition = "serial")
    @Expose
    private int orderId;

    /**
     * Order number.
     */
    @Column(name="order_number")
    @Expose
    private String orderNumber;

    /**
     * Identification of Customer.
     */
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @Expose
    private Customer customer;

    /**
     * Order date.
     */
    @Column(name="order_date")
    @Expose
    private Timestamp orderDate;

    /**
     * Price order.
     */
    @Column(name="total_amount")
    @Expose
    private double totalAmount;

    /**
     * Products by order.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"),
            inverseJoinColumns = @JoinColumn(name ="product_id",referencedColumnName = "product_id")
    )
    private List<Product> products = new ArrayList<>();
}
