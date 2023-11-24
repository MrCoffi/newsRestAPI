package com.example.news.service;

import com.example.news.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUsersByName(String name);

    Optional<User> findUserById(Long id);

    List<User> findAll(Integer pageNumber,Integer pageSize);

    User update(User user);

    User save(User user);

    void delete(Long id);
}
