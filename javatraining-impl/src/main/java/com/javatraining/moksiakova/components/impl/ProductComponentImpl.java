package com.javatraining.moksiakova.components.impl;

import com.javatraining.moksiakova.components.ProductComponent;
import com.javatraining.moksiakova.domain.entity.Product;
import com.javatraining.moksiakova.domain.entity.Supplier;
import com.javatraining.moksiakova.repositories.impl.ProductRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Component  for work with {@link Product}.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ProductComponentImpl implements ProductComponent {

    private ProductRepositoryImpl repository;

    private SupplierComponentImpl supplierComponent;

    @Override
    public Product createProduct(String productName,
                            int supplierId,
                            double unitPrice) {
        Supplier supplierExist = this.supplierComponent.findById(supplierId);
        Product product = new Product();
        product.setProductName(productName);
        product.setSupplier(supplierExist);
        product.setUnitPrice(unitPrice);
        product.setDiscontinued(false);
        repository.save(product);
        log.info("Successfully create Product: {}", product);
        return product;
    }

    @Override
    public Product updateProduct(int productId,
                                String productName,
                                double unitPrice,
                                 int supplierId)
    {
        Supplier supplierExist = this.supplierComponent.findById(supplierId);
        Product product = this.findById(productId);
        product.setProductName(productName);
        product.setUnitPrice(unitPrice);
        product.setSupplier(supplierExist);
        repository.save(product);
        log.info("Successfully update Product: {}", product);
        return product;
    }

    @Override
    public Product findById(int productId) {
        Product product = repository.findOrDie(productId);
        return product;
    }

    @Override
    public void deleteProduct(int productId) {
        Product product = this.findById(productId);
        repository.delete(product);
        log.info("Successfully delete product: {}", product);
    }

    /**
     * Find all {@link Product}.
     * @return
     */
    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }
}
