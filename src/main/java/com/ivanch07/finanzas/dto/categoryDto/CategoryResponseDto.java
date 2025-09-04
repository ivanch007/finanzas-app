package com.ivanch07.finanzas.dto.categoryDto;

import com.ivanch07.finanzas.dto.userDto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {

    private Long id;
    private String name;
    private String description;
    private UserResponseDto user;
}
