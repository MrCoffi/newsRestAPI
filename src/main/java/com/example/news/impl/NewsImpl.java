package com.example.news.impl;

import com.example.news.entity.Category;
import com.example.news.entity.News;
import com.example.news.exeption.EntityNotFoundException;
import com.example.news.exeption.UpdateStateException;
import com.example.news.repository.NewsRepository;
import com.example.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class NewsImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final CategoryImpl categoryImpl;

    @Override
    public List<News> findAll() throws EntityNotFoundException {
        return newsRepository.findAll();
    }

    @Override
    public News findById(Long id) throws EntityNotFoundException{
        return newsRepository.findById(id).orElseThrow();
    }

    @Override
    public News save(News news) throws UpdateStateException {
        Category category = categoryImpl.findCategoryById(news.getCategory().getId());
        news.setCategory(category);
        category.getNews().add(news);
        return newsRepository.save(news);
    }

    @Override
    public News update(News news) throws UpdateStateException{
        News updateNews = new News();
        updateNews.setName(news.getName());
        return newsRepository.save(updateNews);
    }

    @Override
    public void deleteById(Long id) throws UpdateStateException{
        newsRepository.deleteById(id);
    }

    @Override
    public List<News> findNewsByCategoryName(String name) throws EntityNotFoundException{
        return newsRepository.findNewsByCategoryName(name);
    }
}
