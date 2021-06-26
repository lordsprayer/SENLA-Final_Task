package com.senla.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer id;
    private String name;
    private CategoryDto category;

    public ProductDto(String name, CategoryDto category) {
        this.name = name;
        this.category = category;
    }
}
