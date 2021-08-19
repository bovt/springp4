package ru.bvt.notesengine.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bvt.notesengine.domain.Category;
import ru.bvt.notesengine.repository.CategoryRepositoryExtended;
import ru.bvt.notesengine.repository.CategoryRepositoryExtendedSimple;
import ru.bvt.notesengine.rest.dto.CategoryDto;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServiceSimple implements CategoryService {

    private CategoryRepositoryExtended repository;

    @Transactional(readOnly = true)
    public List<CategoryDto> getAllCategories() {
        return repository.findAllAsDto();
    }

    @Transactional(readOnly = true)
    public CategoryDto getCategory(long id) {
        return repository.findByIdAsDto(id);
    }

    @Transactional(readOnly = false)
    public long addCategory(CategoryDto categoryDto) {
        long id = categoryDto.getId();
        if (repository.existsById(id)) {
            throw (new IllegalArgumentException("Invalid category Id:" + id));
        }
        categoryDto.setId(0);
        return repository.create(categoryDto);
    }

    @Transactional(readOnly = false)
    public long setCategory(CategoryDto categoryDto) {
        return repository.update(categoryDto);
    }

    @Transactional(readOnly = false)
    public long deleteCategory(long id) {
        Category category = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        repository.delete(category);
        return id;
    }

}
