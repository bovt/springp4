package ru.bvt.notesengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.domain.Note;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAll();
    Optional<Author> findById(long id);
    Author findByName(String name);
}
