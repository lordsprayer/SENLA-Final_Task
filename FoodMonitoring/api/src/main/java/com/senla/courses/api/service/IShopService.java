package com.senla.courses.api.service;

import com.senla.courses.dto.ShopDto;

import java.util.List;

public interface IShopService {
    List<ShopDto> getAllShops();
    ShopDto getShopById(Integer id);
    void saveShop(ShopDto shopDto);
    void deleteShop(Integer id);
    void updateShop(ShopDto shopDto);
}
