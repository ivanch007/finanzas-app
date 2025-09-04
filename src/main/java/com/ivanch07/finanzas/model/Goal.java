package com.ivanch07.finanzas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "goals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre de la meta es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    private String name;

    @NotNull(message = "El monto objetivo es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor que 0")
    @Digits(integer = 12, fraction = 2, message = "Formato inválido: máximo 12 enteros y 2 decimales")
    private BigDecimal targetAmount;

    @DecimalMin(value = "0.00", message = "El monto actual no puede ser negativo")
    @Digits(integer = 12, fraction = 2, message = "Formato inválido: máximo 12 enteros y 2 decimales")
    private BigDecimal currentAmount = BigDecimal.ZERO;

    @Future(message = "La fecha límite debe ser en el futuro")
    private LocalDate deadLine;

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String description;

    @NotNull(message = "La meta debe pertenecer a un usuario")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
