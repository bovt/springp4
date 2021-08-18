package ru.bvt.notesengine.repository;

import ru.bvt.notesengine.domain.Category;
import ru.bvt.notesengine.rest.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryExtended {

    List<Category> findAll();

    Optional<Category> findById(long id);

    Category save(Category category);

    void deleteById(long id);

    void delete(Category category);

    boolean existsById(Long id);

    Category findByName(String name);

    List<CategoryDto> findAllAsDto();

    CategoryDto findByIdAsDto(long id);

    long create(CategoryDto categoryDto);

    long update(CategoryDto categoryDto);

}
