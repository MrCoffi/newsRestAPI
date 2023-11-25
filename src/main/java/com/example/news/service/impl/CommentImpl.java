package com.example.news.service.impl;

import com.example.news.aop.delete.CommentAop;
import com.example.news.aop.update.CommentUpdate;
import com.example.news.entity.Comment;
import com.example.news.entity.News;
import com.example.news.exeption.EntityNotFoundException;
import com.example.news.exeption.UpdateStateException;
import com.example.news.repository.CommentRepository;
import com.example.news.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final NewsImpl newsImpl;

    @Override
    public Comment save(Comment comment) throws UpdateStateException {
        News news = newsImpl.findById(comment.getNews().getId()).orElseThrow();
        news.getComment().add(comment);
        comment.setNews(news);
        return commentRepository.save(comment);
    }

    @Override
    @CommentUpdate
    public Comment update(Comment comment, Long id) throws UpdateStateException {
        Optional<Comment> existingComment = commentRepository.findCommentById(comment.getId());
        if (existingComment.isPresent()) {
            Comment updatedComment = existingComment.get();
            updatedComment.setName(comment.getName());
            updatedComment.setText(comment.getText());
            return commentRepository.save(updatedComment);
        }
        throw new EntityNotFoundException("Не удалось найти комментарий");
    }

    @Override
    public List<Comment> findAll() throws EntityNotFoundException {
        return commentRepository.findAll();
    }

    @Override
    @CommentAop
    public void deleteById(Long id, Long userId) throws UpdateStateException {
        Optional<Comment> existComment = commentRepository.findCommentById(id);
        if (existComment.isPresent()) {
            Comment deleteComment = existComment.get();
            News news = deleteComment.getNews();
            news.getComment().remove(deleteComment);
            commentRepository.delete(deleteComment);
        }
    }
}
