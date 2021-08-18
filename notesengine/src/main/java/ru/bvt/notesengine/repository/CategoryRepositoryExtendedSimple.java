package ru.bvt.notesengine.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bvt.notesengine.domain.Category;
import ru.bvt.notesengine.rest.dto.CategoryDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CategoryRepositoryExtendedSimple implements CategoryRepositoryExtended {

    private CategoryRepository repository;

    @Autowired
    CategoryRepositoryExtendedSimple(CategoryRepository categoryCRUDRepository) {
        this.repository = categoryCRUDRepository;
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Optional<Category> findById(long id) {
        return repository.findById(id);
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void delete(Category category) {
        repository.delete(category);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Category findByName(String name) {
        return repository.findByName(name);
    }

    public List<CategoryDto> findAllAsDto() {
        return repository.findAll().stream().map(CategoryDto::toDto).collect(Collectors.toList());
    }

    public CategoryDto findByIdAsDto(long id) {
        Category category = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        return new CategoryDto(category);
    }

    public long create(CategoryDto categoryDto) {
        Category category = new Category(categoryDto.getName());
        category = repository.save(category);
        return category.getId();
    }

    public long update(CategoryDto categoryDto) {
        Category category = repository.findById(categoryDto.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + categoryDto.getId()));
        category.setName(categoryDto.getName());
        category = repository.save(category);
        return category.getId();
    }
}
