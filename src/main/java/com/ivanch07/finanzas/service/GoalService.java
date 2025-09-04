package com.ivanch07.finanzas.service;

import com.ivanch07.finanzas.dto.goalDto.GoalRequestDto;
import com.ivanch07.finanzas.dto.goalDto.GoalResponseDto;
import com.ivanch07.finanzas.mappers.GoalMapper;
import com.ivanch07.finanzas.model.Goal;
import com.ivanch07.finanzas.model.User;
import com.ivanch07.finanzas.repositoy.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalService {

    private final GoalRepository goalRepository;
    private final UserService userService;

    public GoalService(GoalRepository goalRepository, UserService userService) {
        this.goalRepository = goalRepository;
        this.userService = userService;
    }

    public List<GoalResponseDto> getGoals() {
        User currentUser = userService.getCurrentUser();
        return goalRepository.findByUser(currentUser)
                .stream()
                .map(GoalMapper::toGoalDto)
                .collect(Collectors.toList());
    }

    public GoalResponseDto createGoal(GoalRequestDto requestDto) {
        User currentUser = userService.getCurrentUser();
        Goal goal = GoalMapper.toEntity(requestDto);
        goal.setUser(currentUser);
        return GoalMapper.toGoalDto(goalRepository.save(goal));
    }

    public GoalResponseDto updateGoal(Long id, GoalRequestDto requestDto) {
        User currentUser = userService.getCurrentUser();

        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meta no encontrada"));

        if (!goal.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("No puedes actualizar una meta que no es tuya");
        }

        goal.setName(requestDto.getName());
        goal.setTargetAmount(requestDto.getTargetAmount());
        goal.setCurrentAmount(requestDto.getCurrentAmount());
        goal.setDeadLine(requestDto.getDeadLine());
        goal.setDescription(requestDto.getDescription());

        return GoalMapper.toGoalDto(goalRepository.save(goal));
    }

    public void deleteGoal(Long id) {
        User currentUser = userService.getCurrentUser();

        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meta no encontrada"));

        if (!goal.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("No puedes eliminar una meta que no es tuya");
        }

        goalRepository.delete(goal);
    }
}
