package com.javatraining.moksiakova.components.impl;

import com.javatraining.moksiakova.components.SupplierComponent;
import com.javatraining.moksiakova.domain.entity.Supplier;
import com.javatraining.moksiakova.repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Component for work with {@link Supplier}.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SupplierComponentImpl implements SupplierComponent {

    /**
     * Component for work with table in database.
     */
    @Autowired
    private SupplierRepository repository;

    @Override
    public Supplier createSupplier(String companyName, String phone) {
        Supplier supplier = new Supplier();
        supplier.setCompanyName(companyName);
        supplier.setPhone(phone);
        repository.save(supplier);
        log.info("Successfully create Supplier: {}", supplier);
        return supplier;
    }

    @Override
    public Supplier updateSupplier(int supplierId, String companyName, String phone) {
        Supplier supplier = repository.findOrDie(supplierId);
        supplier.setCompanyName(companyName);
        supplier.setPhone(phone);
        repository.save(supplier);
        log.info("Successfully update Supplier: {}", supplier);
        return supplier;
    }

    @Override
    public Supplier findById(int supplierId) {
        Supplier supplier = repository.findOrDie(supplierId);
        return supplier;
    }

    @Override
    public List<Supplier> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteSupplier(int supplierId) {
        Supplier supplier = repository.findOrDie(supplierId);
        repository.delete(supplier);
        log.info("Successfully delete Supplier: {}", supplier);
    }
}
