package com.senla.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {
    private Integer id;
    private String name;
    private String address;

    public ShopDto(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
