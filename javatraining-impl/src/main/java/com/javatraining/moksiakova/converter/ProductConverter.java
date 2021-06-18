package com.javatraining.moksiakova.converter;

import com.javatraining.moksiakova.domain.entity.Product;
import com.javatraining.moksiakova.dto.ProductDTO;

public interface ProductConverter {

    ProductDTO convertToDto(Product product);

    Product convertToEntity(ProductDTO productDTO);
}
