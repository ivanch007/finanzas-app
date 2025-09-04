package com.ivanch07.finanzas.dto.transactionDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDto {

    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private Long categoryId;
}
