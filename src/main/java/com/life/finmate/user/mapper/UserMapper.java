package com.life.finmate.user.mapper;

import com.life.finmate.user.domain.User;
import com.life.finmate.user.dto.UserUpdateRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void save(User user);
    Optional<User> findByEmail(String username);
    int updateUser(UserUpdateRequestDto userUpdateRequestDto);
    int deleteById(Long id);
}
