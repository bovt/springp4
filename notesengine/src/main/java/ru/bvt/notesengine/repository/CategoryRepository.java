package ru.bvt.notesengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.domain.Category;
import ru.bvt.notesengine.domain.Note;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAll();
    Optional<Category> findById(long id);
    boolean existsById(Long id);
    Category findByName(String name);

}
