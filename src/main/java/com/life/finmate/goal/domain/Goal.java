package com.life.finmate.goal.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
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

    public void update(String goalName, String goalType, Long targetAmount, LocalDate targetDate) {
        if (goalName != null) this.goalName = goalName;
        if (goalType != null) this.goalType = goalType;
        if (targetAmount != null) this.targetAmount = targetAmount;
        if (targetDate != null) this.targetDate = targetDate;
    }

}
