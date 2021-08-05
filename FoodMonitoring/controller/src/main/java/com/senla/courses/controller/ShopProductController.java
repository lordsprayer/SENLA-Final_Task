package com.senla.courses.controller;

import com.senla.courses.api.IPriceComparison;
import com.senla.courses.api.IPriceDynamics;
import com.senla.courses.api.service.IShopProductService;
import com.senla.courses.csv.ShopProductCsv;
import com.senla.courses.dto.ShopProductDto;
import com.senla.courses.util.CsvParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/shopproducts")
@RequiredArgsConstructor
@Log4j2
public class ShopProductController {

    private final IShopProductService shopProductService;
    private final CsvParser csvParser;

    @GetMapping
    public ResponseEntity<List<ShopProductDto>> getAllShopProducts(@RequestParam Integer page, Integer size) {
        log.log(Level.INFO, "Received get all request: /shopproducts");
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(shopProductService.getAllShopProducts(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopProductDto> getById(@PathVariable Integer id){
        log.log(Level.INFO, "Received get request: /shopproducts/" + id);
        return ResponseEntity.ok(shopProductService.getShopProductById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createShopProduct(@RequestBody ShopProductCsv shopProductCsv){
        log.log(Level.INFO, "Received post request: /shopproducts");
        shopProductService.saveShopProduct(shopProductCsv);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShopProduct(@PathVariable Integer id){
        log.log(Level.INFO, "Received delete request: /shopproducts/" + id);
        shopProductService.deleteShopProduct(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateShopProduct(@RequestBody ShopProductDto shopProductDto){
        log.log(Level.INFO, "Received put request: /products");
        shopProductService.updateShopProduct(shopProductDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dynamics/{id}")
    public ResponseEntity<List<IPriceDynamics>> getPriceDynamicsByProductId(@PathVariable Integer id) {
        log.log(Level.INFO, "Received get all request: /shopproducts/dynamics");
        return ResponseEntity.ok(shopProductService.getPriceDynamics(id));
    }

    @GetMapping("/comparisons")
    public ResponseEntity<List<IPriceComparison>> getPriceComparisonByProductId(@RequestParam String date, Integer shop1, Integer shop2) {
        log.log(Level.INFO, "Received get all request: /shopproducts/comparisons");
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(shopProductService.getPriceComparison(localDate, shop1, shop2));
    }

    @PostMapping(value = "/data", consumes = "multipart/form-data")
    public ResponseEntity<Void> saveShopProductsFromFile(@RequestParam("file") MultipartFile file) throws IOException {
        log.log(Level.INFO, "Received post request from file " + file.getOriginalFilename() + ": /shopproducts/data");
        if (file.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<ShopProductCsv> shopProductCsvList = csvParser.parseCsv(file);
            shopProductService.saveFromShopProductCsv(shopProductCsvList);
            return ResponseEntity.noContent().build();
        }
    }
}
