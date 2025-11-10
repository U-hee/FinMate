package com.life.finmate.goal.controller;

import com.life.finmate.goal.domain.Goal;
import com.life.finmate.goal.dto.GoalCreateRequestDto;
import com.life.finmate.goal.dto.GoalResponseDto;
import com.life.finmate.goal.dto.GoalUpdateRequestDto;
import com.life.finmate.goal.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/goal")
public class GoalController {

    private final GoalService goalService;

    @GetMapping()
    public ResponseEntity<List<GoalResponseDto>> findByUserId(@RequestParam Long userId) {
        List<Goal> byUserId = goalService.findByUserId(userId);
        return ResponseEntity.ok(byUserId.stream()
                .map(GoalResponseDto::from)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoalResponseDto> getGoalById(@PathVariable Long id) {
        Goal goalEntity = goalService.findById(id);
        GoalResponseDto responseDto = GoalResponseDto.from(goalEntity);
        return ResponseEntity.ok(responseDto);

    }

    @PostMapping()
    public ResponseEntity<GoalResponseDto> createGoal(@RequestBody GoalCreateRequestDto goalCreateRequestDto) {
        Goal save = goalService.save(goalCreateRequestDto);
        GoalResponseDto responseDto = GoalResponseDto.from(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<GoalResponseDto> updateGoal(@PathVariable Long id, @RequestBody GoalUpdateRequestDto goalUpdateRequestDto) {
        Goal goal = goalService.updateById(id, goalUpdateRequestDto);
        GoalResponseDto responseDto = GoalResponseDto.from(goal);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        goalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
