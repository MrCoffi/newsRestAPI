package com.example.news.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class NewsFilter {
    private String userName;
    private String categoryName;
    private Integer pageNumber;
    private Integer pageSize;
}