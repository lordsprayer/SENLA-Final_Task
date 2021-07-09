package com.senla.courses.api.service;

import com.senla.courses.dto.ProductDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IProductService {
    ProductDto getProductById(Integer id);
    void saveProduct(String name, Integer categoryId);
    void deleteProduct(Integer id);
    void updateProduct(ProductDto productDto);
    List<ProductDto> getProductsByCategory(String category);
    List<ProductDto> getSortProductsBy(Sort sort);
}
