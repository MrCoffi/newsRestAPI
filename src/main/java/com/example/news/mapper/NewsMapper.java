package com.example.news.mapper;

import com.example.news.entity.News;
import com.example.news.model.request.UpsetNewsRequest;
<<<<<<<<< Temporary merge branch 1
import com.example.news.model.response.News2Response;
=========
import com.example.news.model.response.News3Response;
>>>>>>>>> Temporary merge branch 2
import com.example.news.model.response.NewsListResponse;
import com.example.news.model.response.NewsResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(NewsMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = NewsMapperDelegate.class)
public interface NewsMapper {

    News requestToNews(UpsetNewsRequest request);

    @Mapping(source = "newsId", target = "id")
    News requestToNews(Long newsId, UpsetNewsRequest request);

    NewsResponse newsToResponse(News news);

    News3Response oneNewsToResponses(News news);

    default NewsListResponse clientListToClientResponseList(List<News> news) {
        NewsListResponse response = new NewsListResponse();
        response.setNewsResponseList(news.stream()
                .map(this::oneNewsToResponses).collect(Collectors.toList()));
        return response;
    }
}
