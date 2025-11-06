package com.life.finmate.user.service;

import com.life.finmate.user.domain.User;
import com.life.finmate.user.dto.UserRequest;
import com.life.finmate.user.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @DisplayName("메일로 ID 찾기")
    @Test
    void findByEmail() {
        //given
        String email = "test@email.com";

        User user = User.builder()
                .email(email)
                .userName("test")
                .currency("KR")
                .password("1234")
                .build();
        userMapper.save(user);

        //when
        Optional<User> userBEmail = userService.findByEmail(email);

        //then
        assertThat(userBEmail.isPresent()).isTrue();
        assertThat(userBEmail.get().getEmail()).isEqualTo(email);
    }

    @DisplayName("save user")
    @Test
    void saveUser() {
        // given
        String email = "test@email.com";
        String userName = "test";
        String password = "1234";
        String currency = "KR";

        UserRequest userRequest = UserRequest.builder()
                .email(email)
                .userName(userName)
                .password(password)
                .currency(currency)
                .build();


        // when
       User save = userService.save(userRequest);

        // then
        assertThat(save).isNotNull();
    }

    @DisplayName("email 중복 체크되어 실패")
    @Test
    void FailSaveUser() {
        // given
        String email = "test@email.com";
        String userName = "test";
        String password = "1234";
        String currency = "KR";

        UserRequest userRequest = UserRequest.builder()
                .email(email)
                .userName(userName)
                .password(password)
                .currency(currency)
                .build();


        User save = userService.save(userRequest);
        // when

        // then
        // IllegalStateException()
        assertThatThrownBy(() -> userService.save(userRequest))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("User already exists!");
    }
}