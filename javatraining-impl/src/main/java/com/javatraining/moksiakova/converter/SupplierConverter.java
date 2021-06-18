package com.javatraining.moksiakova.converter;

import com.javatraining.moksiakova.domain.entity.Product;
import com.javatraining.moksiakova.domain.entity.Supplier;
import com.javatraining.moksiakova.dto.ProductDTO;
import com.javatraining.moksiakova.dto.SupplierDTO;

public interface SupplierConverter {
    SupplierDTO convertToDto(Supplier supplier);

    Supplier convertToEntity(SupplierDTO supplierDTO);
}
