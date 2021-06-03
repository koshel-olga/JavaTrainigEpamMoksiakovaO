package com.javatraining.moksiakova.service;

import com.javatraining.moksiakova.CustomResponse;
import com.javatraining.moksiakova.components.ProductComponent;
import com.javatraining.moksiakova.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl {
    private final ProductComponent component;

    public CustomResponse<Product> findProduct(int productId) {
        try {
            Product order = component.findById(productId);
            return new CustomResponse<>(200, "Ok", order);
        } catch (EntityNotFoundException e) {
            return new CustomResponse<>(400, e.getMessage(), null);
        }
    }

    public CustomResponse<List<Product>> findAll() {
        List<Product> customers = component.findAll();
        return new CustomResponse<>(200, "Ok", customers);
    }

    public CustomResponse<Product> createProduct(Product product) {
        CustomResponse<Product> customResponse = this.validateParams(product);
        if (customResponse.getCode() == 200) {
            try {
                Product newProduct = component.createProduct(
                        product.getProductName(),
                        product.getSupplier().getSupplierId(),
                        product.getUnitPrice()
                );
                customResponse.setEntity(newProduct); }
            catch (EntityNotFoundException e) {
                customResponse.setCode(404);
                customResponse.setMessage(e.getMessage());
            }
        }
        return customResponse;
    }

    public CustomResponse<Product> updateProduct(Product product) {
        CustomResponse<Product> customResponse = this.validateParams(product);
        if (customResponse.getCode() == 200) {
            try {
                Product updateProduct = component.updateProduct(
                        product.getProductId(),
                        product.getProductName(),
                        product.getUnitPrice(),
                        product.getSupplier().getSupplierId()
                );
                customResponse.setEntity(updateProduct);
            } catch (EntityNotFoundException e) {
                customResponse.setCode(404);
                customResponse.setMessage(e.getMessage());
            }
        }
        return customResponse;
    }

    public CustomResponse<Product> deleteProduct(int productId) {
        int code = 200;
        String message = String.format("Successful delete Product with id %d", productId);
        try {
            component.deleteProduct(productId);
        } catch (EntityNotFoundException e) {
            code = 404;
            message = e.getMessage();
        }
        return new CustomResponse<>(code,message,null);
    }

    private CustomResponse<Product> validateParams(Product product) {
        int code = 200;
        String message = "Ok";
        if (Objects.isNull(product.getProductName())) {
            code = 400;
            message = "Field productName is not set.";
        }
        if (Objects.isNull(product.getUnitPrice())) {
            code = 400;
            message = "Field unitPrice is not set.";
        }
        if (Objects.isNull(product.getSupplier())) {
            code = 400;
            message = "Field supplier is not set.";
        }
        return new CustomResponse<>(code, message, null);
    }
}
