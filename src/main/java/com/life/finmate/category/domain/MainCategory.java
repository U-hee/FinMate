package com.life.finmate.category.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Builder
@Getter
public class MainCategory {
    @Setter
    private Long id;
    private Long userId;
    private String categoryName;
    private String categoryType; // income, expense
    private String icon;
    private String color;
    private Boolean isSystem;
    private LocalDateTime createdAt;

    public void update(String categoryName, String categoryType, String icon, String color) {
        if (this.isSystem) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "변경할 수 없는 카테고리입니다.");
        }
        if (categoryName != null) this.categoryName = categoryName;
        if (categoryType != null) this.categoryType = categoryType;
        if (icon != null) this.icon = icon;
        if (color != null) this.color = color;
    }
}
