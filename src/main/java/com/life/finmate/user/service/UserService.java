package com.life.finmate.user.service;

import com.life.finmate.user.domain.User;
import com.life.finmate.user.dto.UserRequest;
import com.life.finmate.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public Optional<User> findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    public User save(UserRequest userRequest) {
        findByEmail(userRequest.getEmail()).ifPresent(user -> {
            throw new IllegalStateException("User already exists!");
        });
        User user = userRequest.toEntity();
        userMapper.save(user);

        return user;
    }
}
