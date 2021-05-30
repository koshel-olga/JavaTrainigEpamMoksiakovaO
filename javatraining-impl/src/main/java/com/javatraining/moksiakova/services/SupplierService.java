package com.javatraining.moksiakova.services;

import com.javatraining.moksiakova.CustomResponse;
import com.javatraining.moksiakova.components.SupplierComponent;
import com.javatraining.moksiakova.domain.entity.Supplier;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class SupplierService {
    private final SupplierComponent component = new SupplierComponent();

    public CustomResponse<Supplier> findSupplier(int supplierId) {
        try {
            Supplier supplier = component.findById(supplierId);
            return new CustomResponse<>(200, "Ok", supplier);
        } catch (EntityNotFoundException e) {
            return new CustomResponse<>(400, e.getMessage(), null);
        }
    }

    public CustomResponse<List<Supplier>> findAll() {
        List<Supplier> suppliers = component.findAll();
        return new CustomResponse<>(200, "Ok", suppliers);
    }

    public CustomResponse<Supplier> createSupplier(Supplier supplier) {
        CustomResponse<Supplier> customResponse = this.validateParams(supplier);
        if (customResponse.getCode() == 200) {
            Supplier newSupplier = component.createSupplier(supplier.getCompanyName(),
                    supplier.getPhone());
            customResponse.setEntity(newSupplier);
        }
        return customResponse;
    }

    public CustomResponse<Supplier> updateSupplier(Supplier supplier) {
        CustomResponse<Supplier> customResponse = this.validateParams(supplier);
        if (customResponse.getCode() == 200) {
            try {
                Supplier updateSupplier = component.updateSupplier(
                        supplier.getSupplierId(),
                        supplier.getCompanyName(),
                        supplier.getPhone());
                customResponse.setEntity(updateSupplier);
            } catch (EntityNotFoundException e) {
                customResponse.setCode(404);
                customResponse.setMessage(e.getMessage());
            }
        }
        return customResponse;
    }

    public CustomResponse<Supplier> deleteSupplier(int supplierId) {
        int code = 200;
        String message = String.format("Successful delete Supplier with id %d", supplierId);
        try {
            component.deleteSupplier(supplierId);
        } catch (EntityNotFoundException e) {
            code = 404;
            message = e.getMessage();
        }
        return new CustomResponse<>(code,message,null);
    }

    private CustomResponse<Supplier> validateParams(Supplier supplier) {
        int code = 200;
        String message = "Ok";
        if (Objects.isNull(supplier.getCompanyName())) {
            code = 400;
            message = "Field companyName is not set.";
        }
        if (Objects.isNull(supplier.getPhone())) {
            code = 400;
            message = "Field phone is not set.";
        }
        return new CustomResponse<>(code, message, null);
    }
}
