package ru.bvt.notesengine.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.domain.Note;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findAll();

    Optional<Author> findById(long id);

    Author save(Author author);

    void deleteById(long id);

    void delete(Author author);

    boolean existsById(Long id);

    Author findByName(String name);
}
