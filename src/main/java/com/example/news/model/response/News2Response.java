package com.example.news.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class News2Response {
    private Long id;
    private String name;
    private String text;

}
