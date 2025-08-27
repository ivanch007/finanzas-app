package com.ivanch07.finanzas.service;

import com.ivanch07.finanzas.dto.BudgetRequestDto;
import com.ivanch07.finanzas.dto.BudgetResponseDto;
import com.ivanch07.finanzas.mappers.BudgetMapper;
import com.ivanch07.finanzas.model.Budget;
import com.ivanch07.finanzas.model.User;
import com.ivanch07.finanzas.repositoy.BudgetRepository;
import com.ivanch07.finanzas.repositoy.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserService userService;
    private final CategoryRepository categoryRepository;


    public BudgetService(BudgetRepository budgetRepository, UserService userService, CategoryRepository categoryRepository) {
        this.budgetRepository = budgetRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    public List<BudgetResponseDto> getBudget(){
        User currentUser = userService.getCurrentUser();

        return budgetRepository.findByUser(currentUser)
                .stream()
                .map(BudgetMapper::toBudgetDto)
                .collect(Collectors.toList());
    }

    public BudgetResponseDto createBudget(BudgetRequestDto budgetRequestDto){
        User currentUser = userService.getCurrentUser();

        Budget budget = BudgetMapper.toEntity(budgetRequestDto, categoryRepository);

        budget.setUser(currentUser);
        return BudgetMapper.toBudgetDto(budgetRepository.save(budget));
    }

    public BudgetResponseDto updateBudget(Long id, BudgetRequestDto budgetRequestDto) {
        User currentUser = userService.getCurrentUser();

        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presupuesto no encontrado"));

        if (!budget.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("No puedes actualizar este presupuesto");
        }

        budget.setAmount(budgetRequestDto.getAmount());
        budget.setPeriod(budgetRequestDto.getPeriod());
        budget.setNote(budgetRequestDto.getNote());

        budget.setCategory(
                categoryRepository.findById(budgetRequestDto.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Categoría no encontrada"))
        );

        return BudgetMapper.toBudgetDto(budgetRepository.save(budget));
    }


    public void deleteBudget(Long id){
        User currentUser = userService.getCurrentUser();

        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presupuesto no encontrado"));

        if (!budget.getUser().getId().equals(currentUser.getId())){
            throw new RuntimeException("No puedes actualizar el presupuesto");
        }

        budgetRepository.delete(budget);

    }
}
