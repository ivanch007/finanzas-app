package com.ivanch07.finanzas.controller;

import com.ivanch07.finanzas.dto.goalDto.GoalRequestDto;
import com.ivanch07.finanzas.dto.goalDto.GoalResponseDto;
import com.ivanch07.finanzas.service.GoalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public ResponseEntity<List<GoalResponseDto>> getGoals() {
        return ResponseEntity.ok(goalService.getGoals());
    }

    @PostMapping
    public ResponseEntity<GoalResponseDto> createGoal(@RequestBody GoalRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(goalService.createGoal(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GoalResponseDto> updateGoal(
            @PathVariable Long id,
            @RequestBody GoalRequestDto requestDto
    ) {
        return ResponseEntity.ok(goalService.updateGoal(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }
}
