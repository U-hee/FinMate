package com.life.finmate.transaction.dto;

import com.life.finmate.transaction.domain.Transaction;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionListResponseDto {
    private Long id;
    private Long accountId;
    private Long mainCategoryId;
    private Long subCategoryId;
    private Long amount;
    private LocalDate transactionDate;
    private String description;

    public static TransactionListResponseDto from(Transaction transaction) {
        return TransactionListResponseDto.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccountId())
                .mainCategoryId(transaction.getMainCategoryId())
                .subCategoryId(transaction.getSubCategoryId())
                .amount(transaction.getAmount())
                .transactionDate(transaction.getTransactionDate())
                .description(transaction.getDescription())
                .build();
    }
}
