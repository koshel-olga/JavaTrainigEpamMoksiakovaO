package com.javatraining.moksiakova.domain.entity;

import com.google.gson.annotations.Expose;
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
    @Expose
    private int supplierId;

    /**
     * Name of company.
     */
    @Column(name="company_name")
    @Expose
    private String companyName;

    /**
     * Phone of company.
     */
    @Column(name="phone")
    @Expose
    private String phone;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
}