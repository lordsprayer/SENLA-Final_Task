package com.senla.courses.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shops_products")
public class ShopProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "cost")
    private Double cost;
    @Column(name = "date")
    private LocalDate date;

    public ShopProduct(Shop shop, Product product, Double cost, LocalDate date) {
        this.shop = shop;
        this.product = product;
        this.cost = cost;
        this.date = date;
    }
}