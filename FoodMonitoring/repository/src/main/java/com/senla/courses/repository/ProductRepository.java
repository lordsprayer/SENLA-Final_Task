package com.senla.courses.repository;

import com.senla.courses.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory_name(String category, Pageable pageable);
    Page<Product> findAll(Pageable pageable);
}
