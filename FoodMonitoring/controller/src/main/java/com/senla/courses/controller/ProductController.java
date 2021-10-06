package com.senla.courses.controller;

import com.senla.courses.api.IPriceDynamics;
import com.senla.courses.dto.ProductDto;
import com.senla.courses.api.service.IProductService;
import com.senla.courses.util.PageCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final IProductService productService;
    private final PageCheck pageCheck;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Integer id){
        log.log(Level.INFO, "Received get request: /products/" + id);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody String name, @RequestParam Integer category){
        log.log(Level.INFO, "Received post request: /products");
        productService.saveProduct(name, category);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        log.log(Level.INFO, "Received delete request: /products/" + id);
        productService.deleteProduct(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto){
        log.log(Level.INFO, "Received put request: /products");
        productService.updateProduct(id, productDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@RequestParam String category, Integer page, Integer size) {
        log.log(Level.INFO, "Received get all request: /products");
        size = pageCheck.checkPage(size);
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.getProductsByCategory(category, pageable));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getSortProducts(@RequestParam(defaultValue = "id") String param, Integer page, Integer size) {
        log.log(Level.INFO, "Received get all request: /products");
        size = pageCheck.checkPage(size);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, param));
        return ResponseEntity.ok(productService.getSortProductsBy(pageable));
    }

    @GetMapping("/{id}/dynamics")
    public ResponseEntity<List<IPriceDynamics>> getPriceDynamicsByProductId(@PathVariable Integer id) {
        log.log(Level.INFO, "Received get all request: /products/" + id + "dynamics");
        return ResponseEntity.ok(productService.getPriceDynamics(id));
    }

    //todo добавить в каком-то виде сравнение по всем магазинам одного либо группы продуктов
}
