package com.javatraining.moksiakova.service;

import com.javatraining.moksiakova.dto.SupplierDTO;

import java.util.Collection;

public interface SupplierService {

    SupplierDTO findSupplier(Integer supplierId);

    Collection<SupplierDTO> findAll();

    SupplierDTO createSupplier(SupplierDTO supplier);

    SupplierDTO updateSupplier(SupplierDTO supplier);

    void deleteSupplier(Integer supplierId);
}
