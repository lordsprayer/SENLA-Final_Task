package com.senla.courses.repository;

import com.senla.courses.api.IPriceComparison;
import com.senla.courses.model.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Page<Shop> findAll(Pageable pageable);

    @Query(value = "SELECT p.name AS productName, sp_a.cost AS firstCost, sp_b.cost AS secondCost FROM Product p " +
            "JOIN p.shopProducts sp_a JOIN p.shopProducts sp_b JOIN sp_a.shop s_a JOIN sp_b.shop s_b " +
            "WHERE sp_a.date = :date AND sp_b.date = :date AND s_a.id = :shop1 AND s_b.id = :shop2")
    List<IPriceComparison> comparisonPricesByShops(
            @Param("date") LocalDate date, @Param("shop1")Integer shop1, @Param("shop2")Integer shop2);
}
