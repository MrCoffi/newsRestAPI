package com.example.news.repository;

import com.example.news.entity.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    void deleteCategoriesById(Long id);
    Category findCategoriesByName(String name);
    List<Category> findCategoriesByUserId(Long id);

    @Override
    @EntityGraph(attributePaths = {"news"})
    List<Category> findAll();
}
