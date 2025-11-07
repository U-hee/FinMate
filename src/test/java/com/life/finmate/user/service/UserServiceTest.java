package com.life.finmate.user.service;

import com.life.finmate.user.domain.User;
import com.life.finmate.user.dto.UserCreateRequest;
import com.life.finmate.user.dto.UserUpdateRequest;
import com.life.finmate.user.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

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

    @DisplayName("사용자 저장 테스트")
    @Test
    void saveUser() {
        // given
        String email = "test@email.com";
        String userName = "test";
        String password = "1234";
        String currency = "KR";

        UserCreateRequest userRequest = UserCreateRequest.builder()
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

    @DisplayName("email 중복 체크되어 실패 테스트")
    @Test
    void FailSaveUser() {
        // given
        String email = "test@email.com";
        String userName = "test";
        String password = "1234";
        String currency = "KR";

        UserCreateRequest userRequest = UserCreateRequest.builder()
                .email(email)
                .userName(userName)
                .password(password)
                .currency(currency)
                .build();


        // when
        userService.save(userRequest);

        // then
        assertThatThrownBy(() -> userService.save(userRequest))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("User already exists!");
    }

    @DisplayName("사용자 정보 업데이트 성공 테스트")
    @Test
    void userUpdateSuccessfully() {
        // given
        String email = "test@email.com";
        String userName = "test";
        String password = "1234";
        String currency = "KR";

        String newUserName = "tester";

        UserCreateRequest originUser = UserCreateRequest.builder()
                .email(email)
                .userName(userName)
                .password(password)
                .currency(currency)
                .build();

        User save = userService.save(originUser);
        UserUpdateRequest updateUser= UserUpdateRequest.builder()
                .id(save.getId())
                .email(email)
                .userName(newUserName)
                .build();

        // when
        userService.update(updateUser);

        Optional<User> byEmail = userMapper.findByEmail(updateUser.getEmail());
        // then
        assertThat(byEmail).isNotNull();
        assertThat(byEmail.get().getUserName()).isEqualTo(newUserName);
        assertThat(byEmail.get().getPassword()).isEqualTo(password);
        assertThat(byEmail.get().getCurrency()).isEqualTo(currency);
    }

    @DisplayName("사용자 삭제 테스트")
    @Test
    void deleteByUserId() {
        // given
        String email = "test@email.com";
        String userName = "test";
        String password = "1234";
        String currency = "KR";

        UserCreateRequest userRequest = UserCreateRequest.builder()
                .email(email)
                .userName(userName)
                .password(password)
                .currency(currency)
                .build();
        User save = userService.save(userRequest);

        // when
        int result = userService.deleteById(save.getId());

        // then
        assertThat(result).isEqualTo(1);
        assertThat(userMapper.findByEmail(save.getEmail()).isPresent()).isFalse();
    }
}