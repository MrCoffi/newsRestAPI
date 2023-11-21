package com.example.news.model.response;


import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsListResponse {
    private List<News3Response> newsResponseList;
}
