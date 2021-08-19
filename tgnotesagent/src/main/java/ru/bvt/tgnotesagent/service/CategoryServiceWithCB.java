package ru.bvt.tgnotesagent.service;

import ru.bvt.tgnotesagent.rest.dto.CategoryDto;

import java.util.List;

public interface CategoryServiceWithCB {
    public List<CategoryDto> getAllCategories();
}
