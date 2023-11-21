package com.example.news.model.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Long id;

    @NotEmpty(message = "Заголовок комментария не может быть пустым!")
    private String name;

    @NotEmpty(message = "Комментарий не может быть пустым!")
    private String text;

    @NotNull(message = "Владелец комментария не может быть пустым")
    private Long userId;
}