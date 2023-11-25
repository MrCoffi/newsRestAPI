package com.example.news.controller;

import com.example.news.mapper.CategoryMapper;
import com.example.news.model.request.UpsetCategoryRequest;
import com.example.news.model.response.CategoryResponse;
import com.example.news.model.response.UpdatedCategoryResponse;
import com.example.news.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class NewsCategoryController {
    private final CategoryService categoryServices;
    private final CategoryMapper mapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(@RequestBody @Valid UpsetCategoryRequest request) {
        return mapper.categoryToResponse(categoryServices.save(mapper.requestToCategory(request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoryServices.deleteCategoriesById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public UpdatedCategoryResponse update(@RequestBody UpsetCategoryRequest request) {
        return mapper.updatedCategoryToResponse(categoryServices
                .update(mapper.requestToCategory(request)));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getAll(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return mapper.categoryListToResponseList(categoryServices.findAll(pageNumber, pageSize));
    }
}
