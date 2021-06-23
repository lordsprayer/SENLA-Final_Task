package com.senla.couses.api.service;

import com.senla.courses.dto.ProductDto;

import java.util.List;

public interface IProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Integer id);
    void saveProduct(String name, Integer categoryId);
    void deleteProduct(Integer id);
    void updateProduct(ProductDto productDto);
}
