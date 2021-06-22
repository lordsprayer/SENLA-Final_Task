package com.senla.courses.mapper;

import com.senla.courses.dto.ShopProductDto;
import com.senla.courses.model.ShopProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ShopProductMapper {

    @Mapping(source = "shop", target = "shopDto")
    @Mapping(source = "product", target = "productDto")
    ShopProductDto shopProductToShopProductDto(ShopProduct shopProduct);

    @Mapping(source = "shopDto", target = "shop")
    @Mapping(source = "productDto", target = "product")
    ShopProduct shopProductDtoToShopProduct(ShopProductDto shopProductDto);

    @Mapping(source = "shop", target = "shopDto")
    @Mapping(source = "product", target = "productDto")
    List<ShopProductDto> shopProductListToShoProductDtoList(List<ShopProduct> shopProducts);

    @Mapping(source = "shopDto", target = "shop")
    @Mapping(source = "productDto", target = "productw")
    List<ShopProduct> shopProductDtoListToShoProductList(List<ShopProductDto> shopProductDtos);
}
