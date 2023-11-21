package com.example.news.aop.delete;


import com.example.news.entity.Comment;
import com.example.news.entity.News;
import com.example.news.entity.User;
import com.example.news.exeption.UpdateStateException;
import com.example.news.repository.CommentRepository;
import com.example.news.repository.NewsRepository;
import com.example.news.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Service
@RequiredArgsConstructor
public class CommentAspect {
    private final CommentRepository commentRepository;

    @Before("@annotation(com.example.news.aop.delete.CommentAop)")
    @Transactional
    public void deleteComment(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        Long commentId = (Long) arguments[0];
        Long userId = (Long) arguments[1];
        Comment comment = commentRepository.findCommentById(commentId);
        if (userId == comment.getUserId()) {
            return;
        }
        throw new UpdateStateException("Вы не являетесь владельцем комментария!");
    }

}
