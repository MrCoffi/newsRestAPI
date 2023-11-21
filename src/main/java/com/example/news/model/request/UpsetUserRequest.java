package com.example.news.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpsetUserRequest {
    @NotEmpty(message = "Имя пользователя не может быть пустым!")
    private String name;
}
