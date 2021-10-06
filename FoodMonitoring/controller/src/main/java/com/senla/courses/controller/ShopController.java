package com.senla.courses.controller;

import com.senla.courses.api.IPriceComparison;
import com.senla.courses.dto.ShopDto;
import com.senla.courses.api.service.IShopService;
import com.senla.courses.util.PageCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ShopController {

    private final IShopService shopService;
    private final PageCheck pageCheck;

    @GetMapping("/shops")
    public ResponseEntity<List<ShopDto>> getAllProducts(@RequestParam Integer page, Integer size) {
        log.log(Level.INFO, "Received get all request: /shops");
        size = pageCheck.checkPage(size);
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(shopService.getAllShops(pageable));
    }

    @GetMapping("/shops/{id}")
    public ResponseEntity<ShopDto> getById(@PathVariable Integer id){
        log.log(Level.INFO, "Received get request: /shops/" + id);
        return ResponseEntity.ok(shopService.getShopById(id));
    }

    @PostMapping("/shops")
    public ResponseEntity<Void> createRequest(@RequestBody ShopDto shopDto){
        log.log(Level.INFO, "Received post request: /shops");
        shopService.saveShop(shopDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/shops/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Integer id){
        log.log(Level.INFO, "Received delete request: /shops/" + id);
        shopService.deleteShop(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/shops/{id}")
    public ResponseEntity<Void> closeRequest(@PathVariable Integer id, @RequestBody ShopDto shopDto){
        log.log(Level.INFO, "Received put request: /shops/" + id);
        shopService.updateShop(id, shopDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comparisons/shops")
    public ResponseEntity<List<IPriceComparison>> getPriceComparisonByProductId(@RequestParam Integer shop1, Integer shop2, String date) {
        log.log(Level.INFO, "Received get all request: /comparisons/shops?shop1=" + shop1 + "&shop2=" + shop2 + "&date=" + date + "");
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(shopService.getPriceComparison(localDate, shop1, shop2));
    }
}
