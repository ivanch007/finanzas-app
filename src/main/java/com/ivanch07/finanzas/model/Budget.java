package com.ivanch07.finanzas.model;

import com.ivanch07.finanzas.util.enumeration.BudgetPeriod;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.math.BigDecimal;

@Entity
@Table(name = "budgets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El monto no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    @Digits(integer = 12, fraction = 2, message = "Formato invalido (max 12 enteros y dos decimales")
    private BigDecimal amount;

    @NotNull(message = "El periodo es obligatorio")
    @Enumerated(EnumType.STRING)
    private BudgetPeriod period;

    private String note;

    @NotNull(message = "La categoría es obligatoria")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
