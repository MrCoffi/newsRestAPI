package com.example.news.mapper;

import com.example.news.entity.User;
import com.example.news.model.request.UpsetUserRequest;
import com.example.news.model.response.UserListResponse;
import com.example.news.model.response.UserOne;
import com.example.news.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CategoryMapper.class, NewsMapper.class, CommentMapper.class})
public interface UserMapper {

    User requestToUser(UpsetUserRequest request);

    @Mapping(source = "userId", target = "id")
    User requestToUser(Long userId, UpsetUserRequest request);

    UserResponse userToResponse(User user);

    UserOne userToOneResponse(User user);

    default UserListResponse clientListToClientResponseList(List<User> clients) {
        UserListResponse response = new UserListResponse();
        response.setUserResponses(clients.stream()
                .map(this::userToResponse).collect(Collectors.toList()));
        return response;
    }
}
