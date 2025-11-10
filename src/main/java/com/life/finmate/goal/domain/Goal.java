package com.life.finmate.goal.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class Goal {
    private final Long id;
    private final Long userId;
    private String goalName;
    private String goalType; // saving, debt_repayment, investment
    private Long targetAmount;
    private final Long currentAmount;
    private LocalDate targetDate;
    private final Boolean isCompleted;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public Goal(Long id, Long userId, String goalName, String goalType, Long targetAmount, Long currentAmount, LocalDate targetDate, Boolean isCompleted, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.goalName = goalName;
        this.goalType = goalType;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.targetDate = targetDate;
        this.isCompleted = isCompleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update(String goalName, String goalType, Long targetAmount, LocalDate targetDate) {
        if (goalName != null) this.goalName = goalName;
        if (goalType != null) this.goalType = goalType;
        if (targetAmount != null) this.targetAmount = targetAmount;
        if (targetDate != null) this.targetDate = targetDate;
    }

}
