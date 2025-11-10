package com.life.finmate.account.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class Account {
    private Long id;
    private Long userId;
    private String accountName;
    private String accountType; // bank, card, cash, savings
    private BigDecimal balance;
    private Boolean isActive;
    private LocalDateTime createdAt;
}
