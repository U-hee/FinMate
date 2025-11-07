package com.life.finmate.goal.mapper;

import com.life.finmate.goal.domain.Goal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoalMapper {
    void save(Goal goal);
    List<Goal> findByUserId(long userId);
}
