package com.example.news.controller;

import com.example.news.entity.User;
import com.example.news.exeption.EntityNotFoundException;
import com.example.news.mapper.UserMapper;
import com.example.news.model.request.UpsetUserRequest;
import com.example.news.model.response.UserListResponse;
import com.example.news.model.response.UserOne;
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
    public ResponseEntity<UserListResponse> findAll(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        List<User> users = userService.findAll(pageNumber,pageSize);
        UserListResponse userListResponses = userMapper.clientListToClientResponseList(users);

        return ResponseEntity.ok().body(userListResponses);
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UpsetUserRequest request) {
        User user = userService.save(userMapper.requestToUser(request));
        return ResponseEntity.ok(userMapper.userToResponse(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOne> getUser(@PathVariable Long id) {
        User user = userService.findUserById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return ResponseEntity.ok(userMapper.userToOneResponse(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserResponse> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<UserOne> update(@RequestBody UpsetUserRequest request) {
        User user = userMapper.requestToUser(request);
        userService.update(user);
        return ResponseEntity.ok(userMapper.userToOneResponse(userService.update(user)));
    }
}
