package com.javatraining.moksiakova.components;

import com.javatraining.moksiakova.domain.entity.Supplier;

import java.util.List;

public interface SupplierComponent {

    /**
     * Create {@link Supplier}.
     * @param companyName
     * @param phone
     */
    Supplier createSupplier(String companyName, String phone);

    /**
     * Update {@link Supplier} by supplier_id.
     * @param supplierId
     * @param companyName
     * @param phone
     */
    Supplier updateSupplier(int supplierId, String companyName, String phone);

    /**
     * Find {@link Supplier} by supplier_id.
     * @param supplierId
     * @return
     */
    Supplier findById(int supplierId);

    /**
     * Find all {@link Supplier}.
     * @return
     */
    List<Supplier> findAll();

    /**
     * Delete {@link Supplier} by supplier_id.
     * @param supplierId
     */
    void deleteSupplier(int supplierId);
}
