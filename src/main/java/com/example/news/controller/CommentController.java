package com.example.news.controller;

import com.example.news.exeption.EntityNotFoundException;
import com.example.news.exeption.UpdateStateException;
import com.example.news.mapper.CommentMapper;
import com.example.news.model.request.UpsetCommentRequest;
import com.example.news.model.response.CommentListResponse;
import com.example.news.model.response.CommentResponse;
import com.example.news.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentMapper commentMapper;
    private final CommentService commentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CommentListResponse get() throws EntityNotFoundException {
        return commentMapper.commentListToCommentResponseList(commentService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CommentResponse save(@RequestBody @Valid UpsetCommentRequest request) throws UpdateStateException{
        return commentMapper.commentToResponse(
                commentService.save(commentMapper.requestToComment(request)));
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public CommentResponse update(@RequestBody @Valid UpsetCommentRequest request, @PathVariable Long userId) throws UpdateStateException{
        return commentMapper.commentToResponse(
                commentService.update(commentMapper.requestToComment(request),userId));
    }

    @DeleteMapping("/{commentId}/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long commentId, @PathVariable Long userId) {
            commentService.deleteById(commentId, userId);
    }
}