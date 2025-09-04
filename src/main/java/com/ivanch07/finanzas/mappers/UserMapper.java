package com.ivanch07.finanzas.mappers;

import com.ivanch07.finanzas.dto.userDto.UserResponseDto;
import com.ivanch07.finanzas.model.User;

public class UserMapper {
    public static UserResponseDto toDto(User user) {
        if (user == null) return null;
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }
}
