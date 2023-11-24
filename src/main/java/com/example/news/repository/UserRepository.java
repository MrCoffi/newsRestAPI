package com.example.news.repository;

import com.example.news.entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUsersByName(String name);

    @Query("SELECT DISTINCT user FROM User user " +
            "LEFT JOIN FETCH user.categories category " +
            "LEFT JOIN FETCH category.news news " +
            "LEFT JOIN FETCH news.comment")
     Page<User> findAllWithCategoriesAndNews( Pageable pageable);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.categories WHERE u.id = :userId")
    Optional<User> findByIdWithCategories(@Param("userId") Long userId);


}
