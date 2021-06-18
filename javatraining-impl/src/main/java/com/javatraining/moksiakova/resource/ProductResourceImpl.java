package com.javatraining.moksiakova.resource;

import com.javatraining.moksiakova.dto.ProductDTO;
import com.javatraining.moksiakova.service.ProductService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductResourceImpl implements ProductResource {

    private final ProductService productService;

    @Override
    public ProductDTO findProduct(Integer id) {
        return productService.findById(id);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }

    @Override
    public void deleteProduct(Integer id) {
        productService.deleteProduct(id);
    }
}
