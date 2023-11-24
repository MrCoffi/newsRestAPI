package com.example.news.service.impl;

import com.example.news.entity.User;
import com.example.news.exeption.EntityNotFoundException;
import com.example.news.exeption.UpdateStateException;
import com.example.news.repository.UserRepository;
import com.example.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public Optional<User> findUsersByName(String name) throws EntityNotFoundException {
        return userRepository.findUsersByName(name);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.of(userRepository.findByIdWithCategories(id).orElseThrow(()
                -> new EntityNotFoundException("Не удалось найти пользователя")));
    }

    @Override
    public List<User> findAll(Integer pageNumber,Integer pageSize) throws EntityNotFoundException {
        return userRepository
                .findAllWithCategoriesAndNews(PageRequest.of(pageNumber,pageSize)).getContent();
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

    @Override
    public void delete(Long id) throws UpdateStateException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.delete(user);
        }
    }
}