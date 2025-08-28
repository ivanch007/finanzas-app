package com.ivanch07.finanzas.mappers;

import com.ivanch07.finanzas.dto.GoalRequestDto;
import com.ivanch07.finanzas.dto.GoalResponseDto;
import com.ivanch07.finanzas.model.Goal;

public class GoalMapper {

    public static GoalResponseDto toGoalDto(Goal goal) {
        return new GoalResponseDto(
                goal.getId(),
                goal.getName(),
                goal.getTargetAmount(),
                goal.getCurrentAmount(),
                goal.getDeadLine(),
                goal.getDescription()
        );
    }

    public static Goal toEntity(GoalRequestDto dto) {
        Goal goal = new Goal();
        goal.setName(dto.getName());
        goal.setTargetAmount(dto.getTargetAmount());
        goal.setCurrentAmount(dto.getCurrentAmount());
        goal.setDeadLine(dto.getDeadLine());
        goal.setDescription(dto.getDescription());
        return goal;
    }
}
