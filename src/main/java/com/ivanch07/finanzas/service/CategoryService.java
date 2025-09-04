package com.ivanch07.finanzas.service;

import com.ivanch07.finanzas.dto.categoryDto.CategoryRequestDto;
import com.ivanch07.finanzas.dto.categoryDto.CategoryResponseDto;
import com.ivanch07.finanzas.mappers.CategoryMapper;
import com.ivanch07.finanzas.model.Category;
import com.ivanch07.finanzas.model.User;
import com.ivanch07.finanzas.repositoy.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;

    public CategoryService(CategoryRepository categoryRepository, UserService userService) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    public List<CategoryResponseDto> getCategories(){
        User currentUser = userService.getCurrentUser();

        return categoryRepository.findByUser(currentUser)
                .stream()
                .map(CategoryMapper::toCategoryDto)
                .collect(Collectors.toList());

    }

    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto){
        User currentUser = userService.getCurrentUser();

        Category category = CategoryMapper.toEntity(
                categoryRequestDto
        );

        category.setUser(currentUser);

        return CategoryMapper.toCategoryDto(categoryRepository.save(category));
    }

    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto){
        User currentUser = userService.getCurrentUser();

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        if (!category.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("No puedes actualizar una categoría que no es tuya");
        }

        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());

        return CategoryMapper.toCategoryDto(categoryRepository.save(category));

    }

    public void deleteCategory(Long id) {
        User currentUser = userService.getCurrentUser();

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        if (!category.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("No puedes eliminar una categoría que no es tuya");
        }

        categoryRepository.delete(category);
    }


}
