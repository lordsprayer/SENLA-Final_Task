package com.senla.courses.mapper;

import com.senla.courses.dto.CategoryDto;
import com.senla.courses.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto categoryToCategoryDto(Category category);

    Category categoryDtoToCategory(CategoryDto categoryDto);

    List<CategoryDto> categoryListToCategoryDtoList(List<Category> categories);

    List<Category> categoryDtoListToCategoryList(List<CategoryDto> categoryDtos);

}
