package com.senla.courses.repository;

import com.senla.courses.api.IPriceDynamics;
import com.senla.courses.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory_name(String category, Pageable pageable);
    Page<Product> findAll(Pageable pageable);

    @Query(value = "SELECT AVG(sp.cost) AS avgCost, sp.date AS currentDate FROM ShopProduct sp " +
            "JOIN sp.product p WHERE p.id = :id GROUP BY currentDate ORDER BY currentDate")
    List<IPriceDynamics> avgProductCostByDate(@Param("id")Integer id);
}
