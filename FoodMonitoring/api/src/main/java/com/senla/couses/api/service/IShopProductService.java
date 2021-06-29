package com.senla.couses.api.service;

import com.senla.courses.api.IPriceComparison;
import com.senla.courses.api.IPriceDynamics;
import com.senla.courses.dto.ShopProductDto;

import java.time.LocalDate;
import java.util.List;

public interface IShopProductService {
    List<ShopProductDto> getAllShopProducts();
    ShopProductDto getShopProductById(Integer id);
    void saveShopProduct(Integer shopId, Integer productId, LocalDate date, Double cost);
    void deleteShopProduct(Integer id);
    void updateShopProduct(ShopProductDto shopProductDto);

    List<IPriceDynamics> getPriceDynamics(Integer id);
    List<IPriceComparison> getPriceComparison(LocalDate date, Integer shop1, Integer shop2);
}
