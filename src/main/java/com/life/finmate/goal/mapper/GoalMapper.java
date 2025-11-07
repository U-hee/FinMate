package com.life.finmate.goal.mapper;

import com.life.finmate.goal.domain.Goal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoalMapper {
    public void save(Goal goal);
}
