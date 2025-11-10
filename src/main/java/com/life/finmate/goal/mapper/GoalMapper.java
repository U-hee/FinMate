package com.life.finmate.goal.mapper;

import com.life.finmate.goal.domain.Goal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GoalMapper {
    void save(Goal goal);
    List<Goal> findByUserId(long userId);
    Optional<Goal> findById(long id);
    void updateById(Goal goal);
    void deleteById(Long id);
}
