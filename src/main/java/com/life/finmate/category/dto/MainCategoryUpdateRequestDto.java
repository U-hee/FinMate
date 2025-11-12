package com.life.finmate.category.dto;

import com.life.finmate.category.domain.MainCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MainCategoryUpdateRequestDto {
    private final String categoryName;
    private final String categoryType; // income, expense
    private final String icon;
    private final String color;
    private final Boolean isSystem;

    @Builder
    public MainCategoryUpdateRequestDto(String categoryName, String categoryType, String icon, String color, Boolean isSystem) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.icon = icon;
        this.color = color;
        this.isSystem = isSystem;
    }

    public MainCategory toEntity() {
        return MainCategory.builder()
                .categoryName(this.categoryName)
                .categoryType(this.categoryType)
                .icon(this.icon)
                .color(this.color)
                .isSystem(this.isSystem)
                .build();
    }

    public MainCategoryUpdateRequestDto from(MainCategory mainCategory) {
        return MainCategoryUpdateRequestDto.builder()
                .categoryName(mainCategory.getCategoryName())
                .categoryType(mainCategory.getCategoryType())
                .icon(mainCategory.getIcon())
                .color(mainCategory.getColor())
                .isSystem(mainCategory.getIsSystem())
                .build();
    }
}
