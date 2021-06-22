package com.javatraining.moksiakova.service.impl;

import com.javatraining.moksiakova.converter.SupplierConverter;
import com.javatraining.moksiakova.domain.entity.Order;
import com.javatraining.moksiakova.domain.entity.Product;
import com.javatraining.moksiakova.domain.entity.Supplier;
import com.javatraining.moksiakova.dto.ProductDTO;
import com.javatraining.moksiakova.dto.SupplierDTO;
import com.javatraining.moksiakova.repositories.SupplierRepository;
import com.javatraining.moksiakova.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    private final SupplierConverter supplierConverter;

    @Override
    public SupplierDTO findSupplier(Integer supplierId) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(supplierId);
        if (supplierOptional.isPresent()) {
            return supplierConverter.convertToDto(supplierOptional.get());
        }
        throw new EntityNotFoundException(String.format("Not found Supplier with Id: %d", supplierId));
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public Collection<SupplierDTO> findAll() {
        Collection<Supplier> suppliers = supplierRepository.findAll();
        Collection<SupplierDTO> suppliersDTO = suppliers.stream()
                .map(supplierConverter::convertToDto)
                .collect(Collectors.toSet());
        return suppliersDTO;
    }

    @Override
    public SupplierDTO createSupplier(SupplierDTO supplier) {
        Supplier supplierEntity = supplierConverter.convertToEntity(supplier);
        supplierEntity.setSupplierId(null);
        supplierRepository.save(supplierEntity);
        return supplierConverter.convertToDto(supplierEntity);
    }

    @Override
    public SupplierDTO updateSupplier(SupplierDTO supplier) {
        Supplier supplierEntity = supplierConverter.convertToEntity(supplier);
        supplierRepository.save(supplierEntity);
        return supplierConverter.convertToDto(supplierEntity);
    }

    @Override
    public void deleteSupplier(Integer supplierId) {
        supplierRepository.deleteById(supplierId);
    }
}
