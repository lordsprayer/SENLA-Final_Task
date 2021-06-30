package com.senla.courses.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.senla.courses.api.IPriceComparison;
import com.senla.courses.api.IPriceDynamics;
import com.senla.courses.csv.ShopProductCsv;
import com.senla.courses.dto.ShopProductDto;
import com.senla.courses.model.ShopProduct;
import com.senla.couses.api.service.IShopProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.MultipartConfigElement;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/shopproducts")
@RequiredArgsConstructor
@Log4j2
public class ShopProductController {

    private final IShopProductService shopProductService;

    @GetMapping
    public ResponseEntity<List<ShopProductDto>> getAllShopProducts() {
        log.log(Level.INFO, "Received get all request: /shopproducts");
        return ResponseEntity.ok(shopProductService.getAllShopProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopProductDto> getById(@PathVariable Integer id){
        log.log(Level.INFO, "Received get request: /shopproducts/" + id);
        return ResponseEntity.ok(shopProductService.getShopProductById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createShopProduct(@RequestParam Integer shop, Integer product, String date, Double cost){
        log.log(Level.INFO, "Received post request: /shopproducts");
        LocalDate localDate = LocalDate.parse(date);
        shopProductService.saveShopProduct(shop, product, localDate, cost);
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
    public ResponseEntity<Void> postShoProductFromFile(@RequestParam("file") MultipartFile file) {
        log.log(Level.INFO, "Received get all request: /shopproducts/data");
        System.out.println("Damn it, it's an uploaded file!!!! " + file.getOriginalFilename());
        // validate file
        if (file.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<ShopProductCsv> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(ShopProductCsv.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<ShopProductCsv> shopProductCsvList = csvToBean.parse();
                shopProductService.saveFromShopProductCsv(shopProductCsvList);
                // TODO: save users in DB?



            } catch (Exception ex) {

            }
        }

        return ResponseEntity.noContent().build();
    }
}
