package com.example.news.repository;

import com.example.news.entity.News;

import jakarta.persistence.criteria.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {

    @Override
    @EntityGraph(attributePaths = {"category", "comment", "category.user"})
    Page<News> findAll(Pageable pageable);

    @Query("SELECT n FROM News n LEFT JOIN FETCH n.category LEFT JOIN FETCH n.comment WHERE n.id = :id")
    Optional<News> findById(@Param("id") Long id);


}
