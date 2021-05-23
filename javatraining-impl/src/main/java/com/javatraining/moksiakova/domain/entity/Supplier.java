package com.javatraining.moksiakova.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private int supplierId;

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
}