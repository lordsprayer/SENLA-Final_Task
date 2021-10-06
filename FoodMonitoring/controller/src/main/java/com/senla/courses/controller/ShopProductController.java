package com.senla.courses.controller;

import com.senla.courses.api.service.IShopProductService;
import com.senla.courses.csv.ShopProductCsv;
import com.senla.courses.dto.ShopProductDto;
import com.senla.courses.util.CsvParser;
import com.senla.courses.util.PageCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/shopproducts")
@RequiredArgsConstructor
@Log4j2
public class ShopProductController {

    private final IShopProductService shopProductService;
    private final CsvParser csvParser;
    private final PageCheck pageCheck;

    @GetMapping
    public ResponseEntity<List<ShopProductDto>> getAllShopProducts(@RequestParam Integer page, Integer size) {
        log.log(Level.INFO, "Received get all request: /shopproducts");
        size = pageCheck.checkPage(size);
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

    //todo мб лучше заменить на ShopProductCsv
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateShopProduct(@PathVariable Integer id, @RequestBody ShopProductDto shopProductDto){
        log.log(Level.INFO, "Received put request: /products/" + id);
        shopProductService.updateShopProduct(id, shopProductDto);
        return ResponseEntity.noContent().build();
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
