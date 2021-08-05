package com.senla.courses.api.service;

import com.senla.courses.api.IPriceComparison;
import com.senla.courses.api.IPriceDynamics;
import com.senla.courses.csv.ShopProductCsv;
import com.senla.courses.dto.ShopProductDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IShopProductService {
    /**
     * Returns list of all shopproducts  (in the {@link ShopProductDto} format)
     *
     * @param pageable pagination information(page number and page size)
     *
     * @return list of all shopproducts  (in the {@link ShopProductDto} format)
     */
    List<ShopProductDto> getAllShopProducts(Pageable pageable);

    /**
     * Returns desired shopproduct (in the {@link ShopProductDto} format)
     *
     * @param id id of the desired shopproduct
     *
     * @return desired shopproduct (in the {@link ShopProductDto} format)
     */
    ShopProductDto getShopProductById(Integer id);

    /**
     * Saves the shopproduct to the database
     *
     * @param shopProductCsv
     */
    void saveShopProduct(ShopProductCsv shopProductCsv);

    /**
     * Deletes a shopproduct from the database
     *
     * @param id id of the shopproduct being deleted
     */
    void deleteShopProduct(Integer id);

    /**
     * Updates a shopproduct in the database
     *
     * @param shopProductDto updated shopproduct (in the {@link ShopProductDto} format)
     */
    void updateShopProduct(ShopProductDto shopProductDto);

    /**
     * Returns list containing the average product price for all stores by date
     *
     * @param id product id
     *
     * @return list containing the average product price for all stores by date
     */
    List<IPriceDynamics> getPriceDynamics(Integer id);

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

    /**
     * Saves list of shopproduct to the database
     *
     * @param shopProductCsvList list of shopproduct (in the {@link ShopProductCsv} format)
     */
    void saveFromShopProductCsv(List<ShopProductCsv> shopProductCsvList);
}
