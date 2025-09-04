package com.ivanch07.finanzas.model;


import com.ivanch07.finanzas.util.enumeration.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String description;

    @NotNull(message = "El monto objetivo es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor que 0")
    @Digits(integer = 12, fraction = 2, message = "Formato inválido: máximo 12 enteros y 2 decimales")
    private BigDecimal amount;


    @NotNull(message = "Debe tener un tipo")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Future(message = "La fecha límite debe ser en el futuro")
    private LocalDate date;

    @NotNull(message = "La transación debe pertenecer a un usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
