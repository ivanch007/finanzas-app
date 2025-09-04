package com.ivanch07.finanzas.mappers;

import com.ivanch07.finanzas.dto.categoryDto.CategoryRequestDto;
import com.ivanch07.finanzas.dto.categoryDto.CategoryResponseDto;
import com.ivanch07.finanzas.model.Category;

public class CategoryMapper {

    public static CategoryResponseDto toCategoryDto(Category category){
        if (category == null) return null;
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                UserMapper.toDto(category.getUser())
        );
    }

    public static Category toEntity(CategoryRequestDto categoryRequestDto){

        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());

        return category;

    }
}
