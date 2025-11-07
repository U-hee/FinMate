package com.life.finmate.goal.dto;

import com.life.finmate.goal.domain.Goal;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class GoalCreateRequestDto {
    private Long userId;
    private String goalName;
    private String goalType;
    private Long targetAmount;
    private Long currentAmount;
    private LocalDate targetDate;

    public Goal toEntity() {
        return Goal.builder()
                .userId(userId)
                .goalName(goalName)
                .goalType(goalType)
                .targetAmount(targetAmount)
                .currentAmount(currentAmount)
                .targetDate(targetDate)
                .build();
    }
}
