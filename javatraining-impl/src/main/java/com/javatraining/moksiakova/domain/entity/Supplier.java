package com.javatraining.moksiakova.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

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
 * Entity class for table supplier.
 */
@Entity
@Table(name="supplier", schema="moksiakova")
@Data
@NoArgsConstructor
public class Supplier {

    /**
     * Identification supplier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="supplier_id", columnDefinition = "serial")
    private Integer supplierId;

    /**
     * Name of company.
     */
    @Column(name="company_name")
    private String companyName;

    /**
     * Phone of company.
     */
    @Column(name="phone")
    private String phone;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    public Set<Integer> getProductsIds() {
        return products.stream()
        .map(Product::getProductId)
        .collect(Collectors.toSet());
    }
}