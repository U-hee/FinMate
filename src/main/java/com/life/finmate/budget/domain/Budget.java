package com.life.finmate.budget.domain;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public class Budget {
    private Long id;
    private Long userId;
    private Long mainCategoryId;
    private Long subCategoryId;
    private Integer year;
    private Integer month;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
