package com.javatraining.moksiakova.components;

import com.javatraining.moksiakova.domain.entity.Product;
import com.javatraining.moksiakova.domain.entity.Supplier;
import com.javatraining.moksiakova.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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

    public Product findById(int productId) {
        Product product = repository.findOrDie(productId);
        return product;
    }

    public void deleteProduct(int productId) {
        Product product = this.findById(productId);
        repository.delete(product);
        log.info("Successfully delete product: {}", product);
    }

    /**
     * Find all {@link Product}.
     * @return
     */
    public List<Product> findAll() {
        return repository.findAll();
    }
}
