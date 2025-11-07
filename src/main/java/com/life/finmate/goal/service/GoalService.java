package com.life.finmate.goal.service;

import com.life.finmate.goal.domain.Goal;
import com.life.finmate.goal.dto.GoalCreateRequestDto;
import com.life.finmate.goal.mapper.GoalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalMapper goalMapper;

    public Goal save(GoalCreateRequestDto goalCreateRequestDto) {
        Goal goal = goalCreateRequestDto.toEntity();
        goalMapper.save(goal);
        return goal;
    }

    public List<Goal> findByUserId(long userId) {
        List<Goal> result = goalMapper.findByUserId(userId);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
        }
        return result;
    }

    public Goal findById(Long id) {
        return goalMapper.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND" + id)
        );
    }
}
