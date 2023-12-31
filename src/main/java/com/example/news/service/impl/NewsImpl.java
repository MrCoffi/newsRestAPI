package com.example.news.service.impl;

import com.example.news.entity.Category;
import com.example.news.entity.News;
import com.example.news.exeption.EntityNotFoundException;
import com.example.news.exeption.UpdateStateException;
import com.example.news.model.NewsFilter;
import com.example.news.repository.NewsRepository;
import com.example.news.repository.NewsSpecification;
import com.example.news.service.NewsService;
import com.example.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Service
public class NewsImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final CategoryImpl categoryImpl;
    private final UserImpl userImpl;

    @Override
    public List<News> findAll(Pageable pageable) throws EntityNotFoundException {
        return newsRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())).getContent();
    }

    @Override
    public Optional<News> findById(Long id) throws EntityNotFoundException {
        return newsRepository.findById(id);
    }

    @Override
    public News save(News news) throws UpdateStateException {
        Category category = categoryImpl.findCategoryById(news.getCategory().getId());
        news.setCategory(category);
        category.getNews().add(news);
        news.setUser(news.getUser());
        return newsRepository.save(news);
    }

    @Override
    public News update(News news) throws UpdateStateException {
        News updateNews = newsRepository.findById(news.getId()).orElseThrow();
        updateNews.setName(news.getName());
        updateNews.setCategory(categoryImpl.findCategoryById(news.getCategory().getId()));
        updateNews.setText(news.getText());
        return newsRepository.save(updateNews);
    }

    @Override
    public void deleteById(Long id) throws UpdateStateException {
        newsRepository.deleteById(id);
    }

    @Override
    public List<News> filterBy(NewsFilter newsFilter) {
        return newsRepository.findAll(NewsSpecification.withFilter(newsFilter),
                PageRequest.of(newsFilter.getPageNumber(), newsFilter.getPageSize())).getContent();
    }
}
