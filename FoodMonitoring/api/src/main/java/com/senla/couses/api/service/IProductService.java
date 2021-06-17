package com.senla.couses.api.service;

import com.senla.courses.dto.ProductDto;

import java.util.List;

public interface IProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Integer id);
    void saveProduct(ProductDto productDto);
    void deleteProduct(Integer id);
    void updateProduct(ProductDto productDto);
}
