package com.example.news.controller;

import com.example.news.entity.User;
import com.example.news.mapper.UserMapper;
import com.example.news.model.request.UpsetUserRequest;
import com.example.news.model.response.UserListResponse;
import com.example.news.model.response.UserResponse;
import com.example.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserListResponse> findAll() {

        return ResponseEntity.ok().body(
                userMapper.clientListToClientResponseList(userService.findAll()));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UpsetUserRequest request) {
        User user = userService.save(userMapper.requestToUser(request));
        return ResponseEntity.ok(userMapper.userToResponse(user));
    }



}
