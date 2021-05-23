package com.javatraining.moksiakova.components;

import com.javatraining.moksiakova.domain.entity.Supplier;
import com.javatraining.moksiakova.repositories.SupplierRepository;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

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
    public void createSupplier(String companyName, String phone) {
        Supplier supplier = new Supplier();
        supplier.setCompanyName(companyName);
        supplier.setPhone(phone);
        repository.save(supplier);
        log.info("Successfully create Supplier: {}", supplier);
    }

    /**
     * Update {@link Supplier} by supplier_id.
     * @param supplierId
     * @param companyName
     * @param phone
     */
    public void updateSupplier(int supplierId, String companyName, String phone) {
        try {
            Supplier supplier = repository.findOrDie(supplierId);
            supplier.setCompanyName(companyName);
            supplier.setPhone(phone);
            repository.save(supplier);
            log.info("Successfully update Supplier: {}", supplier);
        } catch (EntityNotFoundException e) {
            log.info("Can not update Supplier with Id={}",supplierId);
        }
    }

    /**
     * Find {@link Supplier} by supplier_id.
     * @param supplierId
     * @return
     */
    public Optional<Supplier> findById(int supplierId) {
        try {
            Supplier supplier = repository.findOrDie(supplierId);
            return Optional.of(supplier);
        } catch (EntityNotFoundException e) {
            log.info("Entity Supplier with Id={} not found. Entity not exist.",supplierId);
        }
        return Optional.empty();
    }

    /**
     * Delete {@link Supplier} by supplier_id.
     * @param supplierId
     */
    public void delete(int supplierId) {
        try {
            Supplier supplier = repository.findOrDie(supplierId);
            repository.delete(supplier);
            log.info("Successfully delete Supplier: {}", supplier);
        } catch (EntityNotFoundException e) {
            log.info("Can not delete Supplier with Id={}. Entity not exist.", supplierId);
        }
    }
}
