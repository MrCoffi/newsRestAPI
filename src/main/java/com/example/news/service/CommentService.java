package com.example.news.service;

import com.example.news.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);

    Comment update(Comment comment,Long id);

    List<Comment> findAll();

    void deleteById(Long id, Long userId);


}
