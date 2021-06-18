package com.javatraining.moksiakova.service;

import com.javatraining.moksiakova.dto.SupplierDTO;

public interface SupplierService {

    SupplierDTO findSupplier(Integer supplierId);

    //List<SupplierDTO> findAll();

    SupplierDTO createSupplier(SupplierDTO supplier);

    SupplierDTO updateSupplier(SupplierDTO supplier);

    void deleteSupplier(Integer supplierId);
}
