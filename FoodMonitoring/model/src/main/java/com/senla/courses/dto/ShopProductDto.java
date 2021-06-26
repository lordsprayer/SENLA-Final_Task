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
    private ShopDto shop;
    private ProductDto product;
    private Double cost;
    private LocalDate date;

    public ShopProductDto(ShopDto shop, ProductDto product, Double cost, LocalDate date) {
        this.shop = shop;
        this.product = product;
        this.cost = cost;
        this.date = date;
    }
}
