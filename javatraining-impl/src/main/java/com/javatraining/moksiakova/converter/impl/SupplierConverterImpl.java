package com.javatraining.moksiakova.converter.impl;

import com.javatraining.moksiakova.converter.SupplierConverter;
import com.javatraining.moksiakova.domain.entity.Supplier;
import com.javatraining.moksiakova.dto.SupplierDTO;
import org.springframework.stereotype.Component;

@Component
public class SupplierConverterImpl implements SupplierConverter {

    @Override
    public SupplierDTO convertToDto(Supplier supplier) {
        return SupplierDTO.builder()
                .supplierId(supplier.getSupplierId())
                .companyName(supplier.getCompanyName())
                .phone(supplier.getPhone())
                .products(supplier.getProductsIds())
                .build();
    }

    @Override
    public Supplier convertToEntity(SupplierDTO supplierDTO) {
        final Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierDTO.getSupplierId());
        supplier.setCompanyName(supplierDTO.getCompanyName());
        supplier.setPhone(supplierDTO.getPhone());
        return supplier;
    }
}
