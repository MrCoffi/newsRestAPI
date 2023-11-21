package com.example.news.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpsetNewsRequest {
    @NotEmpty(message = "Заголовок не может быть пустой!")
    private String name;
    @NotEmpty(message = "Новость не может быть пустой!")
    private String text;
    @NotNull(message = "categoryId не может быть null ")
    private Long categoryId;
}
