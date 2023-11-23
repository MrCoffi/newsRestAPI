package com.example.news.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class News3Response {

    private Long id;

    private String name;

    private String creator;

    private String categoryName;

    private Long commentSize;
}
