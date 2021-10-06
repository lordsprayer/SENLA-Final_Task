package com.senla.courses.repository;

import com.senla.courses.model.ShopProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopProductRepository extends JpaRepository<ShopProduct, Integer> {
    Page<ShopProduct> findAll(Pageable pageable);

}
