package com.life.finmate.goal.service;

import com.life.finmate.goal.domain.Goal;
import com.life.finmate.goal.dto.GoalCreateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class GoalServiceTest {


    @Autowired
    private GoalService goalService;

    @Test
    @DisplayName("목표를 저장하는 테스트")
    void save() {
        // given
        GoalCreateRequestDto goalDto = GoalCreateRequestDto.builder()
                .userId(45L)
                .goalType("saving")
                .goalName("저축")
                .targetAmount(10000L)
                .currentAmount(0L)
                .targetDate(LocalDate.now())
                .build();

        // when
        Goal goal = goalService.save(goalDto);

        // then
        assertThat(goal).isNotNull();
        assertThat(goalDto.getGoalName()).isEqualTo(goal.getGoalName());

    }
}