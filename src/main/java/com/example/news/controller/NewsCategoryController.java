package com.example.news.controller;

import com.example.news.entity.Category;
import com.example.news.mapper.CategoryMapper;
import com.example.news.model.request.UpsetCategoryRequest;
import com.example.news.model.response.CategoryResponse;
import com.example.news.model.response.UpdatedCategoryResponse;
import com.example.news.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class NewsCategoryController {
    private final CategoryService categoryServices;
    private final CategoryMapper mapper;

    @PostMapping()
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid UpsetCategoryRequest request) {
        Category category = categoryServices.save(mapper.requestToCategory(request));
        return ResponseEntity.ok(mapper.categoryToResponse(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponse> delete(@PathVariable Long id) {
        categoryServices.deleteCategoriesById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<UpdatedCategoryResponse> update(@RequestBody UpsetCategoryRequest request) {
        Category category = mapper.requestToCategory(request);
        return ResponseEntity.ok(mapper.updatedCategoryToResponse(categoryServices.update(category)));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll(@RequestParam Integer pageNumber,@RequestParam Integer pageSize) {
        return ResponseEntity.ok(mapper.categoryListToResponseList(categoryServices.findAll(pageNumber, pageSize)));
    }


}
