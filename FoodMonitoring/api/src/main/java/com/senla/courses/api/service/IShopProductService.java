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
     * @param id id of updated shopproducts
     * @param shopProductDto updated shopproduct (in the {@link ShopProductDto} format)
     */
    void updateShopProduct(Integer id, ShopProductDto shopProductDto);

    /**
     * Saves list of shopproduct to the database
     *
     * @param shopProductCsvList list of shopproduct (in the {@link ShopProductCsv} format)
     */
    void saveFromShopProductCsv(List<ShopProductCsv> shopProductCsvList);
}
