package com.senla.courses.mapper;

import com.senla.courses.dto.CategoryDto;
import com.senla.courses.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto categoryToCategoryDto(Category category);

    @Mapping(target = "products", ignore = true)
    Category categoryDtoToCategory(CategoryDto categoryDto);

    List<CategoryDto> categoryListToCategoryDtoList(List<Category> categories);

    @Mapping(target = "products", ignore = true)
    List<Category> categoryDtoListToCategoryList(List<CategoryDto> categoryDtos);

}
