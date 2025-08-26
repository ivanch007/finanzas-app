package com.ivanch07.finanzas.mappers;

import com.ivanch07.finanzas.dto.CategoryRequestDto;
import com.ivanch07.finanzas.dto.CategoryResponseDto;
import com.ivanch07.finanzas.model.Category;

public class CategoryMapper {

    public static CategoryResponseDto toCategoryDto(Category category){
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    public static Category toEntity(CategoryRequestDto categoryRequestDto){

        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());

        return category;

    }
}
