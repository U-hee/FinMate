package com.life.finmate.goal.service;

import com.life.finmate.goal.domain.Goal;
import com.life.finmate.goal.dto.GoalCreateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    @DisplayName("목표를 사용자별로 조회하는 테스트")
    void findAll() {
       //given
        for (int i = 0; i < 5; i++) {
            GoalCreateRequestDto goalDto = GoalCreateRequestDto.builder()
                    .userId(45L)
                    .goalType("saving")
                    .goalName("저축"+i)
                    .targetAmount(10000L)
                    .currentAmount(0L)
                    .targetDate(LocalDate.now())
                    .build();
            goalService.save(goalDto);
        }

       //when
        List<Goal> goals = goalService.findByUserId(45);

       //then
        assertThat(goals).isNotNull();
        assertThat(goals).isNotEmpty();
        assertThat(goals.size()).isEqualTo(5);
        assertThat(goals.getFirst().getGoalName()).isEqualTo("저축0");
    }

    @Test
    @DisplayName("단일 조회 테스트")
    void findById() {
        // given
        long userId = 45L;
        String goalName = "저축";
        GoalCreateRequestDto goalDto = GoalCreateRequestDto.builder()
                .userId(userId)
                .goalType("saving")
                .goalName(goalName)
                .targetAmount(10000L)
                .currentAmount(0L)
                .targetDate(LocalDate.now())
                .build();
        goalService.save(goalDto);
        long id = goalService.findByUserId(userId).getFirst().getId();

        // when
        Goal resultGoal = goalService.findById(id);

        // then
        assertThat(resultGoal).isNotNull();
        assertThat(resultGoal.getGoalName()).isEqualTo(goalName);
    }
}