package com.life.finmate.category.dto;

import com.life.finmate.category.domain.MainCategory;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MainCategoryCreateRequestDto {
    private final Long userId;
    private final String categoryName;
    private final String categoryType; // income, expense
    private final String icon;
    private final String color;
    private final Boolean isSystem;
    private final LocalDateTime createdAt;

    @Builder
    public MainCategoryCreateRequestDto(Long userId, String categoryName, String categoryType, String icon, String color, Boolean isSystem, LocalDateTime createdAt) {
        this.userId = userId;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.icon = icon;
        this.color = color;
        this.isSystem = isSystem;
        this.createdAt = createdAt;
    }

    public MainCategory toEntity() {
        return MainCategory.builder()
                .userId(this.userId)
                .categoryName(this.categoryName)
                .categoryType(this.categoryType)
                .icon(this.icon)
                .color(this.color)
                .isSystem(this.isSystem)
                .createdAt(this.createdAt)
                .build();
    }

    public MainCategoryCreateRequestDto from(MainCategory mainCategory) {
        return MainCategoryCreateRequestDto.builder()
                .userId(mainCategory.getUserId())
                .categoryName(mainCategory.getCategoryName())
                .categoryType(mainCategory.getCategoryType())
                .icon(mainCategory.getIcon())
                .color(mainCategory.getColor())
                .isSystem(mainCategory.getIsSystem())
                .createdAt(mainCategory.getCreatedAt())
                .build();
    }
}
