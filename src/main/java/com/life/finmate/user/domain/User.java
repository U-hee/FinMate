package com.life.finmate.user.domain;

import lombok.Builder;

@Builder
public class User {
    private long id;
    private String email;
    private String password;
    private String userName;
    private String createdAt;
    private String updatedAt;
}
