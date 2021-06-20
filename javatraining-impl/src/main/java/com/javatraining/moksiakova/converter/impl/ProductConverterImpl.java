package com.javatraining.moksiakova.converter.impl;

import com.javatraining.moksiakova.converter.ProductConverter;
import com.javatraining.moksiakova.domain.entity.Product;
import com.javatraining.moksiakova.domain.entity.Supplier;
import com.javatraining.moksiakova.dto.ProductDTO;
import com.javatraining.moksiakova.repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductConverterImpl implements ProductConverter {

    private final SupplierRepository supplierRepository;

    @Override
    public ProductDTO convertToDto(Product product) {

        return ProductDTO.builder()
                .productId(product.getProductId())
                .isDiscontinued(product.isDiscontinued())
                .productName(product.getProductName())
                .unitPrice(product.getUnitPrice())
                .supplierId(product.getSupplier().getSupplierId())
                .build();
    }

    @Override
    public Product convertToEntity(ProductDTO productDTO) {
        final Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setProductName(productDTO.getProductName());
        product.setDiscontinued(productDTO.getIsDiscontinued());
        product.setUnitPrice(productDTO.getUnitPrice());
        final Optional<Supplier> supplier = supplierRepository.findById(productDTO.getSupplierId());
        if (supplier.isPresent()) {
            product.setSupplier(supplier.get());
        }
        return product;
    }
}
