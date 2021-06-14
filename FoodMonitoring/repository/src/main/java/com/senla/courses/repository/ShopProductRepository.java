package com.senla.courses.repository;

import com.senla.courses.model.ShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopProductRepository extends JpaRepository<ShopProduct, Integer> {
}
