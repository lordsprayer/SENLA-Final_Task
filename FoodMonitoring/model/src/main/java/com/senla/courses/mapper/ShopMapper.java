package com.senla.courses.mapper;

import com.senla.courses.dto.ShopDto;
import com.senla.courses.model.Shop;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShopMapper {

    ShopDto shopToShopDto(Shop shop);

    Shop shopDtoToShop(ShopDto shopDto);

    List<ShopDto> shopListToShopDtoList(List<Shop> shops);

    List<Shop> shopDtoListToShopList(List<ShopDto> shopsDtos);

}
