package ru.bvt.notesengine.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.domain.Book;
import ru.bvt.notesengine.domain.Category;
import ru.bvt.notesengine.domain.Note;
import ru.bvt.notesengine.rest.dto.NoteFullDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class NoteRepositoryExtendedSimple implements NoteRepositoryExtended {

    private NoteRepository repository;
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;
    private BookRepository bookRepository;

    public List<Note> findAll() {
        return repository.findAll();
    }

    public Optional<Note> findById(long id) {
        return repository.findById(id);
    }

    public Note save(Note note) {
        return repository.save(note);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public List<NoteFullDto> findAllAsDto() {
        return repository.findAll().stream().map(NoteFullDto::toDto).collect(Collectors.toList());
    }

    public NoteFullDto findByIdAsDto(long id) {
        Note note = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + id));
        return new NoteFullDto(note);
    }

    public long create(Note note) {
        note = repository.save(note);
        return note.getId();
    }

    public long update(Note note) {
        Note resultNote = repository.findById(note.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + note.getId()));
        resultNote.setText(note.getText());
        resultNote.setBook(note.getBook());
        resultNote.setAuthor(note.getAuthor());
        resultNote.setCategories(note.getCategories());
        resultNote = repository.save(resultNote);
        return resultNote.getId();
    }
}
