package com.example.news.service;

import com.example.news.entity.News;
import com.example.news.model.NewsFilter;
import java.util.List;

public interface NewsService {

    List<News> findAll(Integer pageNumber,Integer pageSize);


    News findById(Long id);

    News save(News news);

    News update(News news);

    void deleteById(Long id);

    List<News> filterBy(NewsFilter newsFilter);


}
