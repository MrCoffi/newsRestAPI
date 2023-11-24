package com.example.news.controller;

import com.example.news.entity.News;
import com.example.news.mapper.NewsMapper;
import com.example.news.model.NewsFilter;
import com.example.news.model.request.UpsetNewsRequest;
import com.example.news.model.response.News3Response;
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

    @GetMapping("/filter")
    public ResponseEntity<NewsListResponse> filterBy(NewsFilter newsFilter) {
        return ResponseEntity.ok(newsMapper.clientListToClientResponseList(newsService.filterBy(newsFilter)));
    }


    @GetMapping
    public ResponseEntity<NewsListResponse> getAll(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return ResponseEntity.ok(newsMapper.clientListToClientResponseList(newsService.findAll(pageNumber, pageSize)));
    }

    @PostMapping
    public ResponseEntity<News3Response> create(@RequestBody UpsetNewsRequest request) {
        News news = newsService.save(newsMapper.requestToNews(request));
        return ResponseEntity.ok(newsMapper.oneNewsToResponses(news));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NewsResponse> delete(@PathVariable Long id) {
        newsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<NewsResponse> update(@RequestBody UpsetNewsRequest upsetNewsRequest) {
        News news = newsMapper.requestToNews(upsetNewsRequest);
        return ResponseEntity.ok(newsMapper.newsToResponse(newsService.update(news)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> getOneNews(@PathVariable Long id) {

        return ResponseEntity.ok(newsMapper.newsToResponse(newsService.findById(id)));
    }
}
