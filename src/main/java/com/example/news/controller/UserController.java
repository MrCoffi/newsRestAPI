package com.example.news.controller;

import com.example.news.entity.User;
import com.example.news.exeption.EntityNotFoundException;
import com.example.news.mapper.UserMapper;
import com.example.news.model.request.UpsetUserRequest;
import com.example.news.model.response.OneUserResponse;
import com.example.news.model.response.UserListResponse;
import com.example.news.model.response.UserResponse;
import com.example.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserListResponse findAll(Pageable pageable) {
        return userMapper.clientListToClientResponseList(
                userService.findAll(pageable.getPageNumber(), pageable.getPageSize()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody UpsetUserRequest request) {
        User user = userService.save(userMapper.requestToUser(request));
        return userMapper.userToResponse(user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<OneUserResponse> getUser(@PathVariable Long id) {
        return Optional.of(userMapper.userToOneResponse(userService.findUserById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"))));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public Optional<OneUserResponse> update(@RequestBody UpsetUserRequest request) {
        User user = userMapper.requestToUser(request);
        userService.update(user);
        return Optional.ofNullable(userMapper.userToOneResponse(userService.update(user)));
    }
}
