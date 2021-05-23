package com.javatraining.moksiakova.components;

import com.javatraining.moksiakova.domain.entity.Product;
import com.javatraining.moksiakova.domain.entity.Supplier;
import com.javatraining.moksiakova.repositories.ProductRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * Component  for work with {@link Product}.
 */
@Slf4j
public class ProductComponent {

    private ProductRepository repository;

    private SupplierComponent supplierComponent;

    public ProductComponent() {
        this.repository = new ProductRepository();
        this.supplierComponent = new SupplierComponent();
    }

    public void createProduct(String productName,
                            int supplierId,
                            double unitPrice) {
        Optional<Supplier> supplierExist
                = this.supplierComponent.findById(supplierId);
        if (supplierExist.isPresent()) {
            Product product = new Product();
            product.setProductName(productName);
            product.setSupplier(supplierExist.get());
            product.setUnitPrice(unitPrice);
            product.setDiscontinued(false);
            repository.save(product);
            log.info("Successfully create Product: {}", product);
        } else {
            log.info("Can not create Product with Supplier with id {}",supplierId);
        }
    }

    public void updateProduct(int productId,
                            String productName,
                            double unitPrice)
    {
        Optional<Product> productOptional = this.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setProductName(productName);
            product.setUnitPrice(unitPrice);
            repository.save(product);
            log.info("Successfully update Product: {}", product);
        } else {
            log.info("Can not update Product with Id={}",productId);
        }
    }

    public Optional<Product> findById(int productId) {
        try {
            Product product = repository.findOrDie(productId);
            return Optional.of(product);
        } catch (EntityNotFoundException e) {
            log.info("Entity product with Id={} not found",productId);
        }
        return Optional.empty();
    }

    public void deleteProduct(int productId) {
        Optional<Product> productOptional = this.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            repository.delete(product);
            log.info("Successfully delete product: {}", product);
        } else {
            log.info("Can not delete product with Id={}. Entity not exist.", productId);
        }
    }
}
