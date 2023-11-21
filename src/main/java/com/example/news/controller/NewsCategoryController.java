package com.example.news.controller;

import com.example.news.entity.Category;
import com.example.news.entity.User;
import com.example.news.mapper.CategoryMapper;
import com.example.news.model.request.UpsetCategoryRequest;
import com.example.news.model.response.CategoryResponse;
import com.example.news.service.CategoryService;
import com.example.news.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        return ResponseEntity.ok(mapper.categoryListToResponseList(categoryServices.findAll()));
    }



}
