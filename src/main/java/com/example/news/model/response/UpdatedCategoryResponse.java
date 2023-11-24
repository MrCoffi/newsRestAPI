package com.example.news.model.response;

import lombok.Data;

@Data
public class UpdatedCategoryResponse {
    private Long id;
    private String name;
    private Long userId;
}
