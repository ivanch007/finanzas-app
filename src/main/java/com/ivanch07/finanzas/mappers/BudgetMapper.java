package com.ivanch07.finanzas.mappers;


import com.ivanch07.finanzas.dto.BudgetRequestDto;
import com.ivanch07.finanzas.dto.BudgetResponseDto;
import com.ivanch07.finanzas.model.Budget;
import com.ivanch07.finanzas.model.Category;
import com.ivanch07.finanzas.repositoy.CategoryRepository;

public class BudgetMapper {



    public static BudgetResponseDto toBudgetDto(Budget budget){
        return new BudgetResponseDto(
                budget.getId(),
                budget.getAmount(),
                budget.getPeriod(),
                budget.getNote(),
                budget.getCategory().getId(),
                budget.getCategory().getName()
        );
    }

    public static Budget toEntity(BudgetRequestDto budgetRequestDto, CategoryRepository categoryRepository){
        Budget budget = new Budget();

        budget.setAmount(budgetRequestDto.getAmount());
        budget.setPeriod(budgetRequestDto.getPeriod());
        budget.setNote(budgetRequestDto.getNote());

        Category category = categoryRepository.findById(budgetRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + budgetRequestDto.getCategoryId()));
        budget.setCategory(category);


        return budget;
    }
}
