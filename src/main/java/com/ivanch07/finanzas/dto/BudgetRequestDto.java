package com.ivanch07.finanzas.dto;


import com.ivanch07.finanzas.util.enumeration.BudgetPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetRequestDto {

    private BigDecimal amount;
    private BudgetPeriod period;
    private String note;
    private Long categoryId;
}
