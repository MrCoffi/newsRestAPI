package com.example.news.controller;

import com.example.news.entity.Comment;
import com.example.news.exeption.EntityNotFoundException;
import com.example.news.exeption.UpdateStateException;
import com.example.news.mapper.CommentMapper;
import com.example.news.model.request.UpsetCommentRequest;
import com.example.news.model.response.CommentListResponse;
import com.example.news.model.response.CommentResponse;
import com.example.news.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentMapper commentMapper;

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<CommentListResponse> get() throws EntityNotFoundException {
        return ResponseEntity.ok(commentMapper.commentListToCommentResponseList(commentService.findAll()));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> save(@RequestBody @Valid UpsetCommentRequest request) throws UpdateStateException{
        Comment comment = commentService.save(commentMapper.requestToComment(request));
        return ResponseEntity.ok(commentMapper.commentToResponse(comment));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<CommentResponse> update(@RequestBody @Valid UpsetCommentRequest request, @PathVariable Long userId) throws UpdateStateException{
        Comment comment = commentService.update(commentMapper.requestToComment(request),userId);
        return ResponseEntity.ok(commentMapper.commentToResponse(comment));
    }

    @DeleteMapping("/{commentId}/{userId}")
    public ResponseEntity<CommentResponse> delete(@PathVariable Long commentId, @PathVariable Long userId) {
        try {
            commentService.deleteById(commentId, userId);
        } catch (Exception e) {
                throw new UpdateStateException("Вы не являетесь владельцем комментария.");
        }
        return ResponseEntity.noContent().build();
    }
}