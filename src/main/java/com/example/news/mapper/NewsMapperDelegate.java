package com.example.news.mapper;

import com.example.news.entity.News;
import com.example.news.model.request.UpsetNewsRequest;
import com.example.news.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class NewsMapperDelegate implements NewsMapper {
    @Autowired
    private CategoryService categoryService;

    @Override
    public News requestToNews(UpsetNewsRequest request) {
        News news = new News();
        news.setName(request.getText());
        news.setText(request.getText());
        news.setCategory(categoryService.findCategoryById(request.getCategoryId()));
        return news;
    }

    @Override
    public News requestToNews(Long newsId, UpsetNewsRequest request) {
        News news = requestToNews(request);
        news.setId(newsId);
        return news;
    }

}
