package ru.bvt.jpanotesenginelite.repository;

import ru.bvt.jpanotesenginelite.domain.Note;

import java.util.List;
import java.util.Optional;

public interface NoteRepository {

    List<Note> findAll();

    Optional<Note> findById(long id);

    Note save(Note note);

    long deleteById(long id);
}
