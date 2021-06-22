package com.senal.courses.controller;

import com.senla.courses.dto.ShopProductDto;
import com.senla.couses.api.service.IShopProductService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopproducts")
@RequiredArgsConstructor
public class ShopProductController {

    private static final Logger log = LogManager.getLogger(ShopProductController.class.getName());
    private final IShopProductService shopProductService;

    @GetMapping
    public ResponseEntity<List<ShopProductDto>> getAllProducts() {
        log.log(Level.INFO, "Received get all request: /shopproducts");
        return ResponseEntity.ok(shopProductService.getAllShopProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopProductDto> getById(@PathVariable Integer id){
        log.log(Level.INFO, "Received get request: /shopproducts/" + id);
        return ResponseEntity.ok(shopProductService.getShopProductById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createRequest(@RequestBody ShopProductDto shopProductDto){
        log.log(Level.INFO, "Received post request: /shopproducts");
        shopProductService.saveShopProduct(shopProductDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Integer id){
        log.log(Level.INFO, "Received delete request: /shopproducts/" + id);
        shopProductService.deleteShopProduct(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    public ResponseEntity<Void> closeRequest(@RequestBody ShopProductDto shopProductDto){
        log.log(Level.INFO, "Received put request: /products");
        shopProductService.updateShopProduct(shopProductDto);
        return ResponseEntity.noContent().build();
    }
}
