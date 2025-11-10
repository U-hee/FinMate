package com.life.finmate.user.dto;

import com.life.finmate.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserUpdateRequestDto {
    private long id;
    private String email;
    private String password;
    private String userName;
    private String currency;

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .userName(this.userName)
                .currency(this.currency)
                .build();
    }
}
