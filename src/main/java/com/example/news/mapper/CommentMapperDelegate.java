package com.example.news.mapper;

import com.example.news.entity.Comment;
import com.example.news.entity.User;
import com.example.news.exeption.EntityNotFoundException;
import com.example.news.model.request.UpsetCommentRequest;
import com.example.news.model.response.CommentResponse;
import com.example.news.service.NewsService;
import com.example.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public abstract class CommentMapperDelegate implements CommentMapper {
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;

    @Override
    public Comment requestToComment(UpsetCommentRequest request) throws EntityNotFoundException {
        Comment comment = new Comment();
        comment.setId(request.getId());
        comment.setName(request.getName());
        comment.setText(request.getText());
        comment.setNews(newsService.findById(request.getNewsId()).orElseThrow());
        Optional<User> existUser = userService.findUserById(request.getUserId());
        if (existUser.isPresent()) {
            User user = existUser.get();
            comment.setUser(user);
        }
        return comment;
    }

    @Override
    public CommentResponse commentToResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setName(comment.getName());
        response.setUserId(comment.getUser().getId());
        response.setText(comment.getText());
        return response;
    }

    @Override
    public Comment requestToComment(Long commentId, UpsetCommentRequest request) {
        Comment comment = requestToComment(request);
        comment.setId(commentId);
        return comment;
    }
}
