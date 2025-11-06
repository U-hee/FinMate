package com.life.finmate.user.service;

import com.life.finmate.user.domain.User;
import com.life.finmate.user.dto.UserCreateRequest;
import com.life.finmate.user.dto.UserUpdateRequest;
import com.life.finmate.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public User save(UserCreateRequest userRequest) {
        findByEmail(userRequest.getEmail()).ifPresent(user -> {
            throw new IllegalStateException("User already exists!");
        });
        User user = userRequest.toEntity();
        userMapper.save(user);

        return user;
    }

    public Optional<User> findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    public int update(UserUpdateRequest userRequest) {
        findByEmail(userRequest.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자 입니다."));
        return userMapper.updateUser(userRequest);

    }
}
