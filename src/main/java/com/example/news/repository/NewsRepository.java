package com.example.news.repository;

import com.example.news.entity.News;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Override
    @EntityGraph(attributePaths = {"comment"})
    List<News> findAll();

    Optional<News> findById(Long id);

    List<News> findNewsByCategoryName(String name);

}
