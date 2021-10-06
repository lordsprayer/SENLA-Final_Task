package com.senla.courses.api.service;

import com.senla.courses.api.IPriceComparison;
import com.senla.courses.dto.ShopDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
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
     * @param id id of updated shop
     * @param shopDto updated shop (in the {@link ShopDto} format)
     */
    void updateShop(Integer id,ShopDto shopDto);


    /**
     * Returns list that compares all matching products in 2 stores on a desired date
     *
     * @param date desired date
     * @param shop1  first shop id
     * @param shop2 second shop id
     *
     * @return list that compares all matching products in 2 stores on a desired date
     */
    List<IPriceComparison> getPriceComparison(LocalDate date, Integer shop1, Integer shop2);
}
