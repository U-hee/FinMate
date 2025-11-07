package com.life.finmate.transaction.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class Transaction {
    private Long id;
    private Long userId;
    private Long accountId;
    private Long mainCategoryId;
    private Long subCategoryId;
    private String transactionType; // income, expense
    private BigDecimal amount;
    private LocalDate transactionDate;
    private String description;
    private String receiptUrl;
    private Boolean isRecurring;
    private Long recurringId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
