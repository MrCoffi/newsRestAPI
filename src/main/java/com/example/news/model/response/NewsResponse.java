package com.example.news.model.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponse {
    private Long id;

    private String name;

    @NotEmpty(message = "Новость не может быть пустой!")
    private String text;

    private List<CommentResponse> comment = new ArrayList<>();

}