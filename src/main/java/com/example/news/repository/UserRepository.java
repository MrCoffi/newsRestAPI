package com.example.news.repository;

import com.example.news.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUsersByName(String name);

    void deleteUserById(Long id);

    @Override
    @EntityGraph(attributePaths = {"categories","categories.news","categories.news.comment"})
    List<User> findAll();

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.categories WHERE u.id = :userId")
    Optional<User> findByIdWithCategories(@Param("userId") Long userId);
}
