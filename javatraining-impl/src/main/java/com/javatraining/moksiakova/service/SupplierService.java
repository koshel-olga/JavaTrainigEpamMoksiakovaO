package com.javatraining.moksiakova.service;

import com.javatraining.moksiakova.CustomResponse;
import com.javatraining.moksiakova.domain.entity.Supplier;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface SupplierService {

    public CustomResponse<Supplier> findSupplier(int supplierId);

    public CustomResponse<List<Supplier>> findAll();

    public CustomResponse<Supplier> createSupplier(Supplier supplier);

    public CustomResponse<Supplier> updateSupplier(Supplier supplier);

    public CustomResponse<Supplier> deleteSupplier(int supplierId);
}
