package com.example.news.model.response;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class NewsListResponse {
    private List<NewsResponse> newsResponseList;
}
