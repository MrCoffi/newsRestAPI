package com.example.news.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsFilter {
    private String userName;
    private String categoryName;
    private Integer pageNumber;
    private Integer pageSize;
}
