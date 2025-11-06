package com.life.finmate.user.dto;

import com.life.finmate.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserCreateRequest {
    String email;
    String userName;
    String password;
    String currency;

    public User toEntity()  {
        return User.builder()
                .email(this.email)
                .userName(this.userName)
                .password(this.password)
                .currency(this.currency)
                .build();
    }
}
