package com.example.news.service;

import com.example.news.entity.Category;
import com.example.news.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {

    void deleteCategoriesById(Long id);

    Category findCategoryByName(String name);

    Category findCategoryById(Long id);

    List<Category> findCategoryByUserId(User user);

    Category save(Category category);

    List<Category> findAll();

}
