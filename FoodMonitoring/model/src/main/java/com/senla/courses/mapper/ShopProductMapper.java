package com.senla.courses.mapper;

import com.senla.courses.dto.ShopProductDto;
import com.senla.courses.model.ShopProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ShopProductMapper {

    ShopProductDto shopProductToShopProductDto(ShopProduct shopProduct);

    ShopProduct shopProductDtoToShopProduct(ShopProductDto shopProductDto);

    List<ShopProductDto> shopProductListToShoProductDtoList(List<ShopProduct> shopProducts);

    List<ShopProduct> shopProductDtoListToShoProductList(List<ShopProductDto> shopProductDtos);
}
