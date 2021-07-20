package com.senla.courses.controller;

import com.senla.courses.api.service.ICategoryService;
import com.senla.courses.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Log4j2
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(@RequestParam Integer page, Integer size) {
        log.log(Level.INFO, "Received get all request: /categories");
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(categoryService.getAllCategories(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id){
        log.log(Level.INFO, "Received get request: /categories/" + id);
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody CategoryDto categoryDto){
        log.log(Level.INFO, "Received post request: /categories");
        categoryService.saveCategory(categoryDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id){
        log.log(Level.INFO, "Received delete request: /categories/" + id);
        categoryService.deleteCategory(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping()
    public ResponseEntity<Void> updateCategory(@RequestBody CategoryDto categoryDto){
        log.log(Level.INFO, "Received put request: /categories");
        categoryService.updateCategory(categoryDto);
        return ResponseEntity.noContent().build();
    }
}
