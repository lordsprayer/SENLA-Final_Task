package com.senla.courses.repository;

import com.senla.courses.api.IPriceComparison;
import com.senla.courses.api.IPriceDynamics;
import com.senla.courses.model.ShopProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShopProductRepository extends JpaRepository<ShopProduct, Integer> {
    Page<ShopProduct> findAll(Pageable pageable);

//   @Query(value = "SELECT AVG(sp.cost) AS avgCost, " +
//            "sp.date AS currentDate FROM shops_products AS sp " +
//            "INNER JOIN products AS p ON p.id = sp.product_id " +
//            "WHERE p.id = ?1 GROUP BY currentDate", nativeQuery = true)
    @Query(value = "SELECT AVG(sp.cost) AS avgCost, sp.date AS currentDate FROM ShopProduct sp " +
            "JOIN sp.product p WHERE p.id = :id GROUP BY currentDate ORDER BY currentDate")
    List<IPriceDynamics> avgProductCostByDate(@Param("id")Integer id);

//    @Query(value = "SELECT p.name AS productName, sp_a.cost AS firstCost, " +
//            "sp_b.cost AS secondCost FROM products AS p " +
//            "INNER JOIN shops_products AS sp_a ON sp_a.product_id = p.id " +
//            "INNER JOIN shops AS s_a ON sp_a.shop_id = s_a.id " +
//            "INNER JOIN shops_products AS sp_b ON sp_b.product_id = p.id " +
//            "INNER JOIN shops AS s_b ON sp_b.shop_id = s_b.id " +
//            "WHERE sp_a.date = :date AND sp_b.date = :date AND s_a.id = :shop1 AND s_b.id = :shop2", nativeQuery = true)
    @Query(value = "SELECT p.name AS productName, sp_a.cost AS firstCost, sp_b.cost AS secondCost FROM Product p " +
            "JOIN p.shopProducts sp_a JOIN p.shopProducts sp_b JOIN sp_a.shop s_a JOIN sp_b.shop s_b " +
            "WHERE sp_a.date = :date AND sp_b.date = :date AND s_a.id = :shop1 AND s_b.id = :shop2")
    List<IPriceComparison> comparisonPricesByShops(
            @Param("date") LocalDate date, @Param("shop1")Integer shop1, @Param("shop2")Integer shop2);
}
