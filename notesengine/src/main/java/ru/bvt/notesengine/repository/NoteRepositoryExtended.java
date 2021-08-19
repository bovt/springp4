package ru.bvt.notesengine.repository;

import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.domain.Book;
import ru.bvt.notesengine.domain.Category;
import ru.bvt.notesengine.domain.Note;
import ru.bvt.notesengine.rest.dto.NoteFullDto;

import java.util.List;
import java.util.Optional;

public interface NoteRepositoryExtended {

    List<Note> findAll();

    Optional<Note> findById(long id);

    Note save(Note note);

    void deleteById(long id);

    void delete(Note note);

    boolean existsById(Long id);

    List<NoteFullDto> findAllAsDto();

    NoteFullDto findByIdAsDto(long id);

    long create(Note note);

    long update(Note note);
}
