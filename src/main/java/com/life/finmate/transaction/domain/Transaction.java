package com.life.finmate.transaction.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class Transaction {
    private Long id;                     // 거래 ID
    private Long userId;                 // 사용자 ID
    private Long accountId;              // 계좌 ID
    private Long mainCategoryId;         // 메인 카테고리 ID
    private Long subCategoryId;          // 서브 카테고리 ID
    private String transactionType;      // 거래 유형 (income: 수입, expense: 지출)
    private Long amount;                 // 거래 금액
    private LocalDate transactionDate;   // 거래 발생 날짜
    private String description;          // 거래 설명(메모)
    private String receiptUrl;           // 영수증 이미지 URL
    private Boolean isRecurring;         // 반복 거래 여부
    private Long recurringId;            // 반복 거래 그룹 ID
    private LocalDateTime createdAt;     // 생성 일시
    private LocalDateTime updatedAt;     // 수정 일시


    public void update(Long accountId, Long mainCategoryId, Long subCategoryId, Long amount, LocalDate transactionDate, Boolean isRecurring, String description) {
        if (accountId != null) this.accountId = accountId;
        if (mainCategoryId != null) this.mainCategoryId = mainCategoryId;
        if (subCategoryId != null) this.subCategoryId = subCategoryId;
        if (amount != null) this.amount = amount;
        if (transactionDate != null) this.transactionDate = transactionDate;
        if (isRecurring != null) this.isRecurring = isRecurring;
        if (description != null ) this.description = description;
    }
}
