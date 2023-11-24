package com.example.news.mapper;

import com.example.news.entity.News;
import com.example.news.model.request.UpsetNewsRequest;
import com.example.news.model.response.News3Response;
import com.example.news.model.response.NewsResponse;
import com.example.news.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class NewsMapperDelegate implements NewsMapper {
    @Autowired
    private CategoryService categoryService;

    @Override
    public News requestToNews(UpsetNewsRequest request) {
        News news = new News();
        news.setName(request.getName());
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


    @Override
    public News3Response oneNewsToResponses(News news) {
        News3Response news2Response = new News3Response();
        if (news.getComment() == null) {
            news2Response.setCommentSize(0L);
        } else {
            news2Response.setCommentSize((long) news.getComment().size());
        }

        news2Response.setName(news.getName());
        news2Response.setId(news.getId());
        news2Response.setCategoryName(news
                .getCategory()
                .getName());
        news2Response.setCreator(news
                .getCategory()
                .getUser()
                .getName());
        return news2Response;
    }


    @Override
    public NewsResponse newsToResponse(News news) {
        NewsResponse newsResponse = new NewsResponse();
        newsResponse.setId(news.getId());
        newsResponse.setText(news.getText());
        newsResponse.setName(news.getName());
        return newsResponse;
    }
}
