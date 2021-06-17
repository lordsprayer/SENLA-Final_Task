package com.senla.couses.api.service;

import com.senla.courses.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Integer id);
    void saveCategory(CategoryDto categoryDto);
    void deleteCategory(Integer id);
    void updateCategory(CategoryDto categoryDto);
}
