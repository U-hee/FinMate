package com.life.finmate.goal.service;

import com.life.finmate.goal.domain.Goal;
import com.life.finmate.goal.dto.GoalCreateRequestDto;
import com.life.finmate.goal.dto.GoalUpdateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
                .userId(1L)
                .goalType("saving")
                .goalName("저축")
                .targetAmount(10000L)
                .currentAmount(0L)
                .targetDate(LocalDate.now())
                .build();

        // when
        Goal save = goalService.save(goalDto);

        // then
        assertThat(save).isNotNull();
        assertThat(save.getGoalName()).isEqualTo("저축");
    }

    @Test
    @DisplayName("목표를 사용자별로 조회하는 테스트")
    void findAll() {
       //given
        for (int i = 0; i < 5; i++) {
            GoalCreateRequestDto goalDto = GoalCreateRequestDto.builder()
                    .userId(1L)
                    .goalType("saving")
                    .goalName("저축"+i)
                    .targetAmount(10000L)
                    .currentAmount(0L)
                    .targetDate(LocalDate.now())
                    .build();
            goalService.save(goalDto);
        }

       //when
        List<Goal> goals = goalService.findByUserId(1L);

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
        long userId = 1L;
        String goalName = "저축";
        GoalCreateRequestDto goalDto = GoalCreateRequestDto.builder()
                .userId(userId)
                .goalType("saving")
                .goalName(goalName)
                .targetAmount(10000L)
                .currentAmount(0L)
                .targetDate(LocalDate.now())
                .build();
        Goal save = goalService.save(goalDto);

        // when
        Goal resultGoal = goalService.findById(save.getId());

        // then
        assertThat(resultGoal).isNotNull();
        assertThat(resultGoal.getGoalName()).isEqualTo(goalName);
    }

    @DisplayName("정보 업데이트 테스트")
    @Test
    void updateById() {
        //given
        long userId = 1L;
        String goalName = "저축";
        String newGoalName = "저축업데이트";
        GoalCreateRequestDto goalDto = GoalCreateRequestDto.builder()
                .userId(userId)
                .goalType("saving")
                .goalName(goalName)
                .targetAmount(10000L)
                .currentAmount(0L)
                .targetDate(LocalDate.now())
                .build();
        Goal save = goalService.save(goalDto);

        GoalUpdateRequestDto updateGoalData = GoalUpdateRequestDto.builder()
                .id(save.getId())
                .goalName(newGoalName)
                .build();

        //when
        Goal goal = goalService.updateById(updateGoalData);

        //then
        assertThat(goal.getGoalName()).isEqualTo(newGoalName);
    }

    @DisplayName("삭제 테스트")
    @Test
    void deleteById() {
        //given
        GoalCreateRequestDto goalDto = GoalCreateRequestDto.builder()
                .userId(1L)
                .goalType("saving")
                .goalName("저축")
                .targetAmount(10000L)
                .currentAmount(0L)
                .targetDate(LocalDate.now())
                .build();
        Goal save = goalService.save(goalDto);

        //when
        goalService.deleteById(save.getId());

        //then
        assertThatThrownBy(() -> goalService.findById(save.getId()))
                .isInstanceOf(ResponseStatusException.class);
    }
}