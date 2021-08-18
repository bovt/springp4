package ru.bvt.notesengine.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.domain.Category;
import ru.bvt.notesengine.domain.Note;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    List<Category> findAll();

    Optional<Category> findById(long id);

    Category save(Category category);

    void deleteById(long id);

    void delete(Category category);

    boolean existsById(Long id);

    Category findByName(String name);

}
