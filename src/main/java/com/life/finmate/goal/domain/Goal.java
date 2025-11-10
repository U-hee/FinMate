package com.life.finmate.goal.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class Goal {
    private Long id;
    private Long userId;
    private String goalName;
    private String goalType; // saving, debt_repayment, investment
    private Long targetAmount;
    private Long currentAmount;
    private LocalDate targetDate;
    private Boolean isCompleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
