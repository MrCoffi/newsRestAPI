package com.example.news.service;

import com.example.news.entity.News;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NewsService {

    List<News> findAll();

    News findById(Long id);

    News save(News news);

    News update(News news);

    void deleteById(Long id);

    List<News> findNewsByCategoryName(String name);
}
