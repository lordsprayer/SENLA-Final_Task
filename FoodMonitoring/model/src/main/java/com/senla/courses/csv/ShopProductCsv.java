package com.senla.courses.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopProductCsv {
    @CsvBindByName
    private Integer shop;
    @CsvBindByName
    private Integer product;
    @CsvBindByName
    private Double cost;
    @CsvBindByName
    private String date;
}
