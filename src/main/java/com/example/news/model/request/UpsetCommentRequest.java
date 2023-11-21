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
public class UpsetCommentRequest {
    @NotEmpty(message = "Заголовок комментария не может быть пустым!")
    private String name;

    @NotEmpty(message = "Комментарий не быть пустым!")
    private String text;

    @NotNull(message = " Id новости не может быть пустым")
    private Long newsId;
    @NotNull(message = " Id пользователя не может быть пустым")
    private Long userId;
}
