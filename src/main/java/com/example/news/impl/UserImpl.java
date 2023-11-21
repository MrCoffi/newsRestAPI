package com.example.news.impl;

import com.example.news.entity.User;
import com.example.news.exeption.EntityNotFoundException;
import com.example.news.exeption.UpdateStateException;
import com.example.news.repository.UserRepository;
import com.example.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findUsersByName(String name) throws EntityNotFoundException {
        return userRepository.findUsersByName(name);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.of(userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Не удалось найти пользователя")));
    }

    @Override
    @Transactional
    public List<User> findAll() throws EntityNotFoundException {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) throws UpdateStateException {
        User updateUser = userRepository.findById(user.getId()).orElseThrow();
        updateUser.setName(user.getName());
        userRepository.save(updateUser);
        return updateUser;
    }

    @Override
    public User save(User user) throws UpdateStateException {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) throws UpdateStateException {
        userRepository.deleteUserById(id);
    }
}
