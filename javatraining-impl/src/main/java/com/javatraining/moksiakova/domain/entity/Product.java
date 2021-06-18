package com.javatraining.moksiakova.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for table product.
 */
@Entity
@Table(name="product")
@Data
@NoArgsConstructor
public class Product {

    /**
     * Identification product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id", columnDefinition = "serial")
    private Integer productId;

    /**
     * Product name.
     */
    @Column(name = "product_name")
    private String productName;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "is_discontinued")
    private boolean isDiscontinued;

    /**
     * Orders by product.
     */
    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();
}
