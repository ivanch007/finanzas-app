package com.ivanch07.finanzas.dto.budgetDto;

import com.ivanch07.finanzas.util.enumeration.BudgetPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetResponseDto {

    private Long id;
    private BigDecimal amount;
    private BudgetPeriod period;
    private String note;
    private Long categoryId;
    private String categoryName;

}
