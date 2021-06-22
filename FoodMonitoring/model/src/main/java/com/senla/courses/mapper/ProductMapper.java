package com.senla.courses.mapper;

import com.senla.courses.dto.ProductDto;
import com.senla.courses.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category", target = "categoryDto")
    ProductDto productToProductDto(Product product);

    @Mapping(source = "categoryDto", target = "category")
    @Mapping(target = "shopProducts", ignore = true)
    Product productDtoToProduct(ProductDto productDto);

    @Mapping(source = "category", target = "categoryDto")
    List<ProductDto> productListToProductDtoList(List<Product> products);

    @Mapping(source = "categoryDto", target = "category")
    List<Product> productDtoListToProductList(List<ProductDto> productDtos);
}
