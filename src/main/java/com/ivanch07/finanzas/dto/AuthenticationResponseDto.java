package com.ivanch07.finanzas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponseDto {

    private String token;
    private String tokenType = "Bearer";

    public AuthenticationResponseDto(String token) {
        this.token = token;
        this.tokenType = "Bearer";
    }
}
