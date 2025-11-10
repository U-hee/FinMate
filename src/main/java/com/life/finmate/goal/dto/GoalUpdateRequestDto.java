package com.life.finmate.goal.dto;

import com.life.finmate.goal.domain.Goal;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class GoalUpdateRequestDto {
    private Long userId;
    private String goalName;
    private String goalType;
    private Long targetAmount;
    private Long currentAmount;
    private LocalDate targetDate;
    private LocalDateTime updatedAt;

    public Goal toEntity() {
        return Goal.builder()
                .userId(this.userId)
                .goalName(this.goalName)
                .goalType(this.goalType)
                .targetAmount(this.targetAmount)
                .currentAmount(this.currentAmount)
                .targetDate(this.targetDate)
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public GoalUpdateRequestDto from(Goal goal) {
        return GoalUpdateRequestDto.builder()
                .userId(goal.getUserId())
                .goalName(goal.getGoalName())
                .goalType(goal.getGoalType())
                .targetAmount(goal.getTargetAmount())
                .currentAmount(goal.getCurrentAmount())
                .targetDate(goal.getTargetDate())
                .updatedAt(LocalDateTime.now())
                .build();
    }


}
