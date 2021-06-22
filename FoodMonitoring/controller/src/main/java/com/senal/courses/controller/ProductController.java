package com.senal.courses.controller;

import com.senla.courses.dto.ProductDto;
import com.senla.couses.api.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private static final Logger log = LogManager.getLogger(ProductController.class.getName());
    private final IProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        log.log(Level.INFO, "Received get all request: /products");
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Integer id){
        log.log(Level.INFO, "Received get request: /products/" + id);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createRequest(@RequestBody ProductDto productDto){
        log.log(Level.INFO, "Received post request: /products");
        productService.saveProduct(productDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Integer id){
        log.log(Level.INFO, "Received delete request: /products/" + id);
        productService.deleteProduct(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    public ResponseEntity<Void> closeRequest(@RequestBody ProductDto productDto){
        log.log(Level.INFO, "Received put request: /products");
        productService.updateProduct(productDto);
        return ResponseEntity.noContent().build();
    }
}
