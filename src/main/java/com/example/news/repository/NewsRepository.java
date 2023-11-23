package com.example.news.repository;

import com.example.news.entity.News;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Override
    @Query("SELECT n FROM News n LEFT JOIN FETCH n.category LEFT JOIN FETCH n.comment")
    List<News> findAll();

    @Query("SELECT n FROM News n LEFT JOIN FETCH n.category LEFT JOIN FETCH n.comment WHERE n.id = :id")
    Optional<News> findById(@Param("id") Long id);



    List<News> findNewsByCategoryName(String name);

}
