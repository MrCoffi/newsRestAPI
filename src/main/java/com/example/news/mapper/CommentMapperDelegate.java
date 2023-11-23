package com.example.news.mapper;

import com.example.news.entity.Comment;
import com.example.news.exeption.EntityNotFoundException;
import com.example.news.model.request.UpsetCommentRequest;
import com.example.news.service.NewsService;
import com.example.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

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
        System.out.println(request.getNewsId());
        comment.setNews(newsService.findById(request.getNewsId()));
        if (userService.findUserById(request.getUserId()).isPresent()) {
            comment.setUserId(request.getUserId());
        }
        return comment;
    }

    @Override
    public Comment requestToComment(Long commentId, UpsetCommentRequest request) {
        Comment comment = requestToComment(request);
        comment.setId(commentId);
        return comment;
    }


}
