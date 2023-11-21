package com.example.news.model.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOne {
    private Long id;

    @NotEmpty(message = "Имя пользователя не может быть пустым!")
    private String name;
}
