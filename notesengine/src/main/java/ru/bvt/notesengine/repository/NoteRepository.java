package ru.bvt.notesengine.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.domain.Note;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends CrudRepository<Note, Integer> {

    List<Note> findAll();

    Optional<Note> findById(long id);

    Note save(Note note);

    void deleteById(long id);

    void delete(Note note);

    boolean existsById(Long id);
}
