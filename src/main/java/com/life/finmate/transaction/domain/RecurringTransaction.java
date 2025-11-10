package com.life.finmate.transaction.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class RecurringTransaction {
    private Long id;
    private Long userId;
    private Long accountId;
    private Long mainCategoryId;
    private Long subCategoryId;
    private String transactionType; // income, expense
    private BigDecimal amount;
    private String description;
    private String frequency; // daily, weekly, monthly, yearly
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate nextOccurrence;
    private Boolean isActive;
    private LocalDateTime createdAt;
}
