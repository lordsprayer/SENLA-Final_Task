package com.senla.courses.controller;

import com.senla.courses.dto.ShopDto;
import com.senla.courses.api.service.IShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
@RequiredArgsConstructor
@Log4j2
public class ShopController {

    private final IShopService shopService;

    @GetMapping
    public ResponseEntity<List<ShopDto>> getAllProducts() {
        log.log(Level.INFO, "Received get all request: /shops");
        return ResponseEntity.ok(shopService.getAllShops());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopDto> getById(@PathVariable Integer id){
        log.log(Level.INFO, "Received get request: /shops/" + id);
        return ResponseEntity.ok(shopService.getShopById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createRequest(@RequestBody ShopDto shopDto){
        log.log(Level.INFO, "Received post request: /shops");
        shopService.saveShop(shopDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Integer id){
        log.log(Level.INFO, "Received delete request: /shops/" + id);
        shopService.deleteShop(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    public ResponseEntity<Void> closeRequest(@RequestBody ShopDto shopDto){
        log.log(Level.INFO, "Received put request: /shops");
        shopService.updateShop(shopDto);
        return ResponseEntity.noContent().build();
    }
}
