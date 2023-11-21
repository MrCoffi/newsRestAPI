package com.example.news.model.response;

import lombok.Data;

import java.util.List;

@Data
public class CommentListResponse {
    private List<CommentResponse> commentResponseList;
}
