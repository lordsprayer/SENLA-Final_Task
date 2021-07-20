package com.senla.courses.api.service;

import com.senla.courses.dto.ShopDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IShopService {
    /**
     * Returns list of all shops (in the {@link ShopDto} format)
     *
     * @param pageable pagination information(page number and page size)
     *
     * @return list of all shops (in the {@link ShopDto} format)
     */
    List<ShopDto> getAllShops(Pageable pageable);

    /**
     * Returns desired shop (in the {@link ShopDto} format)
     *
     * @param id id of the desired shop
     *
     * @return desired shop (in the {@link ShopDto} format)
     */
    ShopDto getShopById(Integer id);

    /**
     * Saves the shop to the database
     *
     * @param shopDto saved shop (in the {@link ShopDto} format)
     */
    void saveShop(ShopDto shopDto);

    /**
     * Deletes a shop from the database
     *
     * @param id id of the product being deleted
     */
    void deleteShop(Integer id);

    /**
     * Updates a shop in the database
     *
     * @param shopDto updated shop (in the {@link ShopDto} format)
     */
    void updateShop(ShopDto shopDto);
}
