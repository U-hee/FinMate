package com.life.finmate.user.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class User {
    private long id;
    private String email;
    private String password;
    private String userName;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
