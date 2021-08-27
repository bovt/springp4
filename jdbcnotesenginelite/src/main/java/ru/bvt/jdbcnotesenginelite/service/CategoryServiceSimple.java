package ru.bvt.jdbcnotesenginelite.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bvt.jdbcnotesenginelite.domain.Category;
import ru.bvt.jdbcnotesenginelite.repository.CategoryRepository;
import ru.bvt.jdbcnotesenginelite.rest.dto.CategoryDto;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryServiceSimple implements CategoryService {

    private final CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDto> getAllCategories() {
        return repository.findAll().stream().map(CategoryDto::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDto getCategory(long id) {
        Category category = repository.findById(id);
        if (category == null) {
            return null;
        }
        ;
        return new CategoryDto(category);
    }

    @Transactional(readOnly = false)
    public long addCategory(CategoryDto categoryDto) {
        long id = categoryDto.getId();
        if (repository.existsById(id)) {
            throw (new IllegalArgumentException("Invalid category Id:" + id));
        }
        Category category = new Category(categoryDto.getName());
        category = repository.save(category);
        return category.getId();
    }

    @Transactional(readOnly = false)
    public long setCategory(CategoryDto categoryDto) {
        Category category = repository.findById(categoryDto.getId());
        if (category == null) {
            return 0;
        }
        ;
        category.setName(categoryDto.getName());
        category = repository.save(category);
        return category.getId();
    }

    @Transactional(readOnly = false)
    public long deleteCategory(long id) {
        return repository.deleteById(id);
    }

}
