package com.example.news.mapper;

import com.example.news.entity.News;
import com.example.news.model.request.UpsetNewsRequest;
import com.example.news.model.response.News3Response;
import com.example.news.model.response.NewsResponse;
import com.example.news.service.CategoryService;
import com.example.news.service.NewsService;
import com.example.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class NewsMapperDelegate implements NewsMapper {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    @Override
    public News requestToNews(UpsetNewsRequest request) {
        News news = new News();
        news.setName(request.getName());
        news.setText(request.getText());
        news.setId(request.getId());
        news.setCategory(categoryService.findCategoryById(request.getCategoryId()));
        news.setUser(userService.findUserById(request.getUserId()).orElseThrow());
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
        if (news.getId() != null) {
            news = newsService.findById(news.getId()).orElseThrow();

            news2Response.setId(news.getId());
        }
        if (news.getComment() == null) {
            news2Response.setCommentSize(0L);
        } else {
            news2Response.setCommentSize((long) news.getComment().size());
        }
        if (news.getName() != null) {
            news2Response.setName(news.getName());
        }

        if (news.getCategory() != null && news.getCategory().getUser().getName() != null) {
            news2Response.setCategoryName(news
                    .getCategory()
                    .getName());

            news2Response.setCreator(news
                    .getCategory()
                    .getUser()
                    .getName());
        }
        return news2Response;
    }
}