package com.example.news.service;

import com.example.news.entity.News;
import com.example.news.model.NewsFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NewsService {

    List<News> findAll(Pageable pageable);

    Optional<News> findById(Long id);

    News save(News news);

    News update(News news);

    void deleteById(Long id);

    List<News> filterBy(NewsFilter newsFilter);

}
