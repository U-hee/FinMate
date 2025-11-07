package com.life.finmate.category.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MainCategory {
    private Long id;
    private Long userId;
    private String categoryName;
    private String categoryType; // income, expense
    private String icon;
    private String color;
    private Boolean isSystem;
    private LocalDateTime createdAt;
}
