package com.ivanch07.finanzas.controller;

import com.ivanch07.finanzas.dto.budgetDto.BudgetRequestDto;
import com.ivanch07.finanzas.dto.budgetDto.BudgetResponseDto;
import com.ivanch07.finanzas.service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public ResponseEntity<List<BudgetResponseDto>> getBudgets() {
        return ResponseEntity.ok(budgetService.getBudget());
    }

    @PostMapping
    public ResponseEntity<BudgetResponseDto> createBudget(@RequestBody BudgetRequestDto budgetRequestDto) {
        return ResponseEntity.ok(budgetService.createBudget(budgetRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetResponseDto> updateBudget(
            @PathVariable Long id,
            @RequestBody BudgetRequestDto budgetRequestDto
    ) {
        return ResponseEntity.ok(budgetService.updateBudget(id, budgetRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }
}
