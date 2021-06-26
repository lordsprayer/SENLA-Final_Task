package com.senla.courses.mapper;

import com.senla.courses.dto.ProductDto;
import com.senla.courses.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto);

    List<ProductDto> productListToProductDtoList(List<Product> products);

    List<Product> productDtoListToProductList(List<ProductDto> productDtos);
}
