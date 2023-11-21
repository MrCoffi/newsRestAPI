package com.example.news.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpsetCategoryRequest {
    private Long id;
    @NotNull(message = "userId не может быть пустым")
    private Long userId;
    private String name;
}
