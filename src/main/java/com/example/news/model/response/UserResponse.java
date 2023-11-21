package com.example.news.model.response;

import com.example.news.entity.Comment;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;

    @NotEmpty(message = "Имя пользователя не может быть пустым!")
    private String name;

    private List<CategoryResponse> categories = new ArrayList<>();


}
