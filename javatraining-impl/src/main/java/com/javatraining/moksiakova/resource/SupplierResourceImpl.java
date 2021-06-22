package com.javatraining.moksiakova.resource;

import com.javatraining.moksiakova.dto.OrderDTO;
import com.javatraining.moksiakova.dto.SupplierDTO;
import com.javatraining.moksiakova.service.SupplierService;
import lombok.RequiredArgsConstructor;

import java.util.Collection;


@RequiredArgsConstructor
public class SupplierResourceImpl implements SupplierResource {
    private final SupplierService supplierService;

    @Override
    public SupplierDTO findSupplier(Integer id) {
        return supplierService.findSupplier(id);
    }

    @Override
    public Collection<SupplierDTO> findAllSupplier() {
        Collection<SupplierDTO> suppliers = supplierService.findAll();
        return suppliers;
    }

    @Override
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        return supplierService.createSupplier(supplierDTO);
    }

    @Override
    public SupplierDTO updateSupplier(SupplierDTO supplierDTO) {
        return supplierService.updateSupplier(supplierDTO);
    }

    @Override
    public void deleteSupplier(Integer id) {
        supplierService.deleteSupplier(id);
    }
}
