package com.example.news.mapper;

import com.example.news.entity.Comment;
import com.example.news.model.request.UpsetCommentRequest;
import com.example.news.model.response.CommentListResponse;
import com.example.news.model.response.CommentResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(CommentMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    Comment requestToComment(UpsetCommentRequest request);

    @Mapping(source = "commentId", target = "id")
    Comment requestToComment(Long commentId, UpsetCommentRequest request);

    CommentResponse commentToResponse(Comment comment);

    default CommentListResponse commentListToCommentResponseList(List<Comment> comments) {
        CommentListResponse response = new CommentListResponse();
        response.setCommentResponseList(comments.stream()
                .map(this::commentToResponse).collect(Collectors.toList()));
        return response;
    }

}
