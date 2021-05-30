package com.javatraining.moksiakova.components;

import com.javatraining.moksiakova.domain.entity.Supplier;
import com.javatraining.moksiakova.repositories.SupplierRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Component for work with {@link Supplier}.
 */
@Slf4j
public class SupplierComponent {

    /**
     * Component for work with table in database.
     */
    private SupplierRepository repository;

    public SupplierComponent() {
        this.repository = new SupplierRepository();
    }

    /**
     * Create {@link Supplier}.
     * @param companyName
     * @param phone
     */
    public Supplier createSupplier(String companyName, String phone) {
        Supplier supplier = new Supplier();
        supplier.setCompanyName(companyName);
        supplier.setPhone(phone);
        repository.save(supplier);
        log.info("Successfully create Supplier: {}", supplier);
        return supplier;
    }

    /**
     * Update {@link Supplier} by supplier_id.
     * @param supplierId
     * @param companyName
     * @param phone
     */
    public Supplier updateSupplier(int supplierId, String companyName, String phone) {
        Supplier supplier = repository.findOrDie(supplierId);
        supplier.setCompanyName(companyName);
        supplier.setPhone(phone);
        repository.save(supplier);
        log.info("Successfully update Supplier: {}", supplier);
        return supplier;
    }

    /**
     * Find {@link Supplier} by supplier_id.
     * @param supplierId
     * @return
     */
    public Supplier findById(int supplierId) {
        Supplier supplier = repository.findOrDie(supplierId);
        return supplier;
    }

    /**
     * Find all {@link Supplier}.
     * @return
     */
    public List<Supplier> findAll() {
        return repository.findAll();
    }

    /**
     * Delete {@link Supplier} by supplier_id.
     * @param supplierId
     */
    public void deleteSupplier(int supplierId) {
        Supplier supplier = repository.findOrDie(supplierId);
        repository.delete(supplier);
        log.info("Successfully delete Supplier: {}", supplier);
    }
}
