package com.life.finmate.transaction.dto;

import com.life.finmate.transaction.domain.Transaction;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionDetailResponseDto {
    private Long id;
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

    public static TransactionDetailResponseDto from(Transaction transaction) {
        return TransactionDetailResponseDto.builder()
                .id(transaction.getId())
                .userId(transaction.getUserId())
                .accountId(transaction.getAccountId())
                .mainCategoryId(transaction.getMainCategoryId())
                .subCategoryId(transaction.getSubCategoryId())
                .transactionType(transaction.getTransactionType())
                .amount(transaction.getAmount())
                .transactionDate(transaction.getTransactionDate())
                .description(transaction.getDescription())
                .receiptUrl(transaction.getReceiptUrl())
                .isRecurring(transaction.getIsRecurring())
                .recurringId(transaction.getRecurringId())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
