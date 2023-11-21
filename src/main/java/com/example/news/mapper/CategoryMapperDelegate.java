package com.example.news.mapper;

import com.example.news.entity.Category;
import com.example.news.model.request.UpsetCategoryRequest;
import com.example.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class CategoryMapperDelegate implements CategoryMapper {

    @Autowired
    private UserService userService;

    @Override
    public Category requestToCategory(UpsetCategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setUser(userService.findUserById(request.getUserId()).orElseThrow());
        return category;
    }

    @Override
    public Category requestToCategory(Long categoryId, UpsetCategoryRequest request) {
        Category category = requestToCategory(request);
        category.setId(categoryId);
        return category;
    }

}
