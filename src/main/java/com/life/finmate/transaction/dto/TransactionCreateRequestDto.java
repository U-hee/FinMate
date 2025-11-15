package com.life.finmate.transaction.dto;

import com.life.finmate.transaction.domain.Transaction;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class TransactionCreateRequestDto {
    private Long userId;
    private Long accountId;
    private Long mainCategoryId;
    private Long subCategoryId;
    private String transactionType; // income, expense
    private Long amount;
    private LocalDate transactionDate;
    private String description;
    private String receiptUrl;
    private Boolean isRecurring;
    private Long recurringId;
    private LocalDateTime createdAt;

    public Transaction toEntity() {
        return Transaction.builder()
                .userId(this.userId)
                .accountId(this.accountId)
                .mainCategoryId(this.mainCategoryId)
                .subCategoryId(this.subCategoryId)
                .transactionType(this.transactionType)
                .amount(this.amount)
                .transactionDate(this.transactionDate)
                .description(this.description)
                .receiptUrl(this.receiptUrl)
                .isRecurring(this.isRecurring)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
