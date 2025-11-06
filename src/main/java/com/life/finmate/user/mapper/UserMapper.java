package com.life.finmate.user.mapper;

import com.life.finmate.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> findByEmail(String username);
    int save(User user);
}
