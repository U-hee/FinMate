package com.life.finmate.goal.dto;

import com.life.finmate.goal.domain.Goal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoalResponseDto {
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


    @Builder
    public GoalResponseDto(Long id, Long userId, String goalName, String goalType, Long targetAmount, Long currentAmount, LocalDate targetDate, Boolean isCompleted, LocalDateTime createdAt, LocalDateTime updatedAt) {
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

    public GoalResponseDto(Goal goal) {
        this.id = goal.getId();
        this.userId = goal.getUserId();
        this.goalName = goal.getGoalName();
        this.goalType = goal.getGoalType();
        this.targetAmount = goal.getTargetAmount();
        this.currentAmount = goal.getCurrentAmount();
        this.targetDate = goal.getTargetDate();
        this.isCompleted = goal.getIsCompleted();
        this.createdAt = goal.getCreatedAt();
        this.updatedAt = goal.getUpdatedAt();
    }

    public static GoalResponseDto from(Goal goal) {
        return GoalResponseDto.builder()
                .id(goal.getId())
                .userId(goal.getUserId())
                .goalName(goal.getGoalName())
                .goalType(goal.getGoalType())
                .targetAmount(goal.getTargetAmount())
                .currentAmount(goal.getCurrentAmount())
                .targetDate(goal.getTargetDate())
                .isCompleted(goal.getIsCompleted())
                .createdAt(goal.getCreatedAt())
                .updatedAt(goal.getUpdatedAt())
                .build();
    }

    public Goal toEntity() {
        return Goal.builder()
                .id(this.id)
                .userId(this.userId)
                .goalName(this.goalName)
                .goalType(this.goalType)
                .targetAmount(this.targetAmount)
                .currentAmount(this.currentAmount)
                .isCompleted(this.isCompleted)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
