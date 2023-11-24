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

@RequiredArgsConstructor
@Service
@Transactional
public class CommentImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final NewsImpl newsImpl;

    @Override
    public Comment save(Comment comment) throws UpdateStateException {
        News news = newsImpl.findById(comment.getNews().getId());
        news.getComment().add(comment);
        comment.setNews(news);
        return commentRepository.save(comment);
    }

    @Override
    @CommentUpdate
    public Comment update(Comment comment, Long id) throws UpdateStateException{
        try {
            Comment updateComment = commentRepository.findCommentById(comment.getId());
            updateComment.setName(comment.getName());
            updateComment.setText(comment.getText());
            return commentRepository.save(updateComment);
        } catch (Exception e) {
            throw new EntityNotFoundException("Не удалось найти сущность");
        }
    }

    @Override
    public List<Comment> findAll() throws EntityNotFoundException {
        return commentRepository.findAll();
    }

    @Override
    @CommentAop
    public void deleteById(Long id, Long userId) throws UpdateStateException {
        commentRepository.deleteById(id);
    }
}
