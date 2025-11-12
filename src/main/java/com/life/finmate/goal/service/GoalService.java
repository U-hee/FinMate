package com.life.finmate.goal.service;

import com.life.finmate.goal.domain.Goal;
import com.life.finmate.goal.dto.GoalCreateRequestDto;
import com.life.finmate.goal.dto.GoalUpdateRequestDto;
import com.life.finmate.goal.mapper.GoalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalMapper goalMapper;

    public Goal createGoal(GoalCreateRequestDto goalCreateRequestDto) {
        Goal goal = goalCreateRequestDto.toEntity();
        goalMapper.save(goal);
        return goal;
    }

    public List<Goal> findByUserId(Long userId) {
        List<Goal> result = goalMapper.findByUserId(userId);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
        }
        return result;
    }

    public Goal findById(Long id) {
        return goalMapper.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND : " + id)
        );
    }

    @Transactional
    public Goal updateById(Long id, GoalUpdateRequestDto updateRequestDto) {
        Goal originGoal = findById(id);
        originGoal.update(updateRequestDto.getGoalName(), updateRequestDto.getGoalType(), updateRequestDto.getTargetAmount(), updateRequestDto.getTargetDate());
        goalMapper.updateById(originGoal);
        return originGoal;
    }

    public void deleteById(Long id) {
        goalMapper.deleteById(id);
    }
}
