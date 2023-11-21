package com.example.news.impl;

import com.example.news.entity.Category;
import com.example.news.entity.User;
import com.example.news.exeption.EntityNotFoundException;
import com.example.news.exeption.UpdateStateException;
import com.example.news.repository.CategoryRepository;
import com.example.news.service.CategoryService;
import com.example.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CategoryImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userServiceImpl;

    @Override
    public void deleteCategoriesById(Long id) throws UpdateStateException {
        categoryRepository.deleteCategoriesById(id);
    }

    @Override
    public Category findCategoryByName(String name) throws EntityNotFoundException{
        return categoryRepository.findCategoriesByName(name);
    }

    @Override
    public Category findCategoryById(Long id) throws EntityNotFoundException{
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Category> findCategoryByUserId(User user) throws EntityNotFoundException{
        return categoryRepository.findCategoriesByUserId(user.getId());
    }

    @Override
    public Category save(Category category) throws UpdateStateException{
        User user = userServiceImpl.findUserById(category.getUser().getId()).orElseThrow();
        category.setUser(user);
        user.getCategories().add(category);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() throws EntityNotFoundException{
        return categoryRepository.findAll();
    }
}
