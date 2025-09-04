package com.ivanch07.finanzas.dto.transactionDto;

import com.ivanch07.finanzas.dto.categoryDto.CategoryResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class TransactionResponseDto {

    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private CategoryResponseDto category;



}
