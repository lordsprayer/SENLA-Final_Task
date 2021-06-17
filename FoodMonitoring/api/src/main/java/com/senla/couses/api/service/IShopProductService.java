package com.senla.couses.api.service;

import com.senla.courses.dto.ShopProductDto;

import java.util.List;

public interface IShopProductService {
    List<ShopProductDto> getAllShopProducts();
    ShopProductDto getShopProductById(Integer id);
    void saveShopProduct(ShopProductDto shopProductDto);
    void deleteShopProduct(Integer id);
    void updateShopProduct(ShopProductDto shopProductDto);
}
