package com.life.finmate.category.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class SubCategory {
    private Long id;
    private Long mainCategoryId;
    private Long userId;
    private String categoryName;
    private String icon;
    private String color;
    private Boolean isSystem;
    private LocalDateTime createdAt;
}
