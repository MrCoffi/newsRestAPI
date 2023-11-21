package com.example.news.controller;

import com.example.news.entity.News;
import com.example.news.mapper.NewsMapper;
import com.example.news.model.request.UpsetNewsRequest;
import com.example.news.model.response.NewsListResponse;
import com.example.news.model.response.NewsResponse;
import com.example.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsMapper newsMapper;
    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<NewsListResponse> getAll() {
        return ResponseEntity.ok(newsMapper.clientListToClientResponseList(newsService.findAll()));
    }

    @PostMapping
    public ResponseEntity<NewsResponse> create(@RequestBody UpsetNewsRequest request) {
        News news = newsService.save(newsMapper.requestToNews(request));
        return ResponseEntity.ok(newsMapper.newsToResponse(news));
    }

}
