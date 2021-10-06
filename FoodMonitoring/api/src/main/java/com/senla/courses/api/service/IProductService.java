package com.senla.courses.api.service;

import com.senla.courses.api.IPriceDynamics;
import com.senla.courses.dto.ProductDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IProductService {
    /**
     * Returns desired product (in the {@link ProductDto} format)
     *
     * @param id id of the desired product
     *
     * @return desired product (in the {@link ProductDto} format)
     */
    ProductDto getProductById(Integer id);

    /**
     * Saves the product to the database
     *
     * @param name product name
     * @param categoryId id of the category
     */
    void saveProduct(String name, Integer categoryId);

    /**
     * Deletes a product from the database
     *
     * @param id id of the product being deleted
     */
    void deleteProduct(Integer id);

    /**
     * Updates a product in the database
     *
     * @param id id of updated product
     * @param productDto updated product (in the {@link ProductDto} format)
     */
    void updateProduct(Integer id, ProductDto productDto);

    /**
     * Returns list of products of a desired  category
     *
     * @param category name of the desired category
     * @param pageable pagination information(page number and page size)
     *
     * @return list of products of a desired  category
     */
    List<ProductDto> getProductsByCategory(String category, Pageable pageable);

    /**
     * Returns list of products sorted by the desired field
     *
     * @param pageable pagination information(page number, page size and sorting parameter (in {@link Sort} format)
     *
     * @return list of products sorted by the desired field
     */
    List<ProductDto> getSortProductsBy(Pageable pageable);

    /**
     * Returns list containing the average product price for all stores by date
     *
     * @param id product id
     *
     * @return list containing the average product price for all stores by date
     */
    List<IPriceDynamics> getPriceDynamics(Integer id);
}
