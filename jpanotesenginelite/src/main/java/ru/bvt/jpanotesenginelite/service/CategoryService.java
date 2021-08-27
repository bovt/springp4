package ru.bvt.jpanotesenginelite.service;

import ru.bvt.jpanotesenginelite.rest.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    long addCategory(CategoryDto newCategoryDto);

    CategoryDto getCategory(long id);

    long setCategory(CategoryDto categoryDto);

    long deleteCategory(long id);

}
