package com.ivanch07.finanzas.controller;

import com.ivanch07.finanzas.dto.CategoryRequestDto;
import com.ivanch07.finanzas.dto.CategoryResponseDto;
import com.ivanch07.finanzas.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto requestDto) {
        return ResponseEntity.ok(categoryService.createCategory(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDto requestDto
    ) {
        return ResponseEntity.ok(categoryService.updateCategory(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
