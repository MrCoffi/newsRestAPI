package com.example.news.controller;

import com.example.news.exeption.EntityNotFoundException;
import com.example.news.mapper.NewsMapper;
import com.example.news.model.NewsFilter;
import com.example.news.model.request.UpsetNewsRequest;
import com.example.news.model.response.News3Response;
import com.example.news.model.response.NewsListResponse;
import com.example.news.model.response.NewsResponse;
import com.example.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsMapper newsMapper;
    private final NewsService newsService;

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public NewsListResponse filterBy(NewsFilter newsFilter) {
        return newsMapper.clientListToClientResponseList(
                newsService.filterBy(newsFilter));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public NewsListResponse getAll(Pageable pageable) {
        return newsMapper.clientListToClientResponseList(
                newsService.findAll(pageable));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public News3Response create(@RequestBody UpsetNewsRequest request) {
        return newsMapper.oneNewsToResponses(
                newsService.save(newsMapper.requestToNews(request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        newsService.deleteById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public NewsResponse update(@RequestBody UpsetNewsRequest upsetNewsRequest) {
        return newsMapper.newsToResponse(
                newsService.update(newsMapper.requestToNews(upsetNewsRequest)));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<NewsResponse> getOneNews(@PathVariable Long id) {
        return Optional.ofNullable(Optional.of(newsMapper.newsToResponse(newsService.findById(id).orElseThrow()))
                .orElseThrow(() -> new EntityNotFoundException("Не найдена новость")));
    }
}
