package com.life.finmate.goal.service;

import com.life.finmate.goal.domain.Goal;
import com.life.finmate.goal.dto.GoalCreateRequestDto;
import com.life.finmate.goal.mapper.GoalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalMapper goalMapper;

    public Goal save(GoalCreateRequestDto goalCreateRequestDto) {
        Goal goal = goalCreateRequestDto.toEntity();
        goalMapper.save(goal);
        return goal;
    }
}
