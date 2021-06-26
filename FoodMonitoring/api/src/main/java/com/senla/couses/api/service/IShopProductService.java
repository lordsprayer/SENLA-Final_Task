package com.senla.couses.api.service;

import com.senla.courses.dto.ShopProductDto;

import java.time.LocalDate;
import java.util.List;

public interface IShopProductService {
    List<ShopProductDto> getAllShopProducts();
    ShopProductDto getShopProductById(Integer id);
    void saveShopProduct(Integer shopId, Integer productId, LocalDate date, Double cost);
    void deleteShopProduct(Integer id);
    void updateShopProduct(ShopProductDto shopProductDto);
}
