package com.example.news.aop.update;

import com.example.news.entity.Comment;
import com.example.news.exeption.UpdateStateException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Service
public class CommentUpdateAspect {

    @Before("@annotation(CommentUpdate)")
    @Transactional
    public void deleteComment(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        Comment comment = (Comment) arguments[0];
        Long userId = (Long) arguments[1];
        if (userId == comment.getUserId()) {
            return;
        }
        throw new UpdateStateException("Вы не являетесь владельцем комментария!");
    }
}