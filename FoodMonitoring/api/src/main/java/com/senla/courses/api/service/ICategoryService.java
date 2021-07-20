package com.senla.courses.api.service;

import com.senla.courses.dto.CategoryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {
    /**
     * Returns list of all categories (in the {@link CategoryDto} format)
     *
     * @param pageable pagination information(page number and page size)
     *
     * @return list of all categories (in the {@link CategoryDto} format)
     */
    List<CategoryDto> getAllCategories(Pageable pageable);

    /**
     * Returns desired category (in the {@link CategoryDto} format)
     *
     * @param id id of the desired category
     *
     * @return desired category (in the {@link CategoryDto} format)
     */
    CategoryDto getCategoryById(Integer id);

    /**
     * Saves the category to the database
     *
     * @param categoryDto saved category (in the {@link CategoryDto} format)
     */
    void saveCategory(CategoryDto categoryDto);

    /**
     * Deletes a category from the database
     *
     * @param id id of the category being deleted
     */
    void deleteCategory(Integer id);

    /**
     * Updates a category in the database
     *
     * @param categoryDto updated category (in the {@link CategoryDto} format)
     */
    void updateCategory(CategoryDto categoryDto);
}
