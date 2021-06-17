package com.senla.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ShopProductDto {
    private Integer id;
    private ShopDto shopDto;
    private ProductDto productDto;
    private Double cost;
    private LocalDate date;

    public ShopProductDto(ShopDto shopDto, ProductDto productDto, Double cost, LocalDate date) {
        this.shopDto = shopDto;
        this.productDto = productDto;
        this.cost = cost;
        this.date = date;
    }
}
