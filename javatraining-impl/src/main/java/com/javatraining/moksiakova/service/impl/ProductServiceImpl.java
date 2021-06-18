package com.javatraining.moksiakova.service.impl;

import com.javatraining.moksiakova.converter.ProductConverter;
import com.javatraining.moksiakova.domain.entity.Product;
import com.javatraining.moksiakova.dto.ProductDTO;
import com.javatraining.moksiakova.repositories.ProductRepository;
import com.javatraining.moksiakova.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductConverter productConverter;

    @Override
    public ProductDTO findById(Integer productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return productConverter.convertToDto(productOptional.get());
        }
        throw new EntityNotFoundException(String.format("Not found Product with Id: %d", productId));
    }

    @Override
    public ProductDTO createProduct(ProductDTO product) {
        Product productEntity = productConverter.convertToEntity(product);
        productEntity.setProductId(null);
        productRepository.save(productEntity);
        return productConverter.convertToDto(productEntity);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO product) {
        Product productEntity = productConverter.convertToEntity(product);
        productRepository.save(productEntity);
        return productConverter.convertToDto(productEntity);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }
}
