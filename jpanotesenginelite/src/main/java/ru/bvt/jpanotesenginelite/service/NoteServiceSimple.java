package ru.bvt.jpanotesenginelite.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bvt.jpanotesenginelite.domain.Author;
import ru.bvt.jpanotesenginelite.domain.Book;
import ru.bvt.jpanotesenginelite.domain.Category;
import ru.bvt.jpanotesenginelite.domain.Note;
import ru.bvt.jpanotesenginelite.repository.AuthorRepository;
import ru.bvt.jpanotesenginelite.repository.BookRepository;
import ru.bvt.jpanotesenginelite.repository.CategoryRepository;
import ru.bvt.jpanotesenginelite.repository.NoteRepository;
import ru.bvt.jpanotesenginelite.rest.dto.NoteBriefDto;
import ru.bvt.jpanotesenginelite.rest.dto.NoteDto;
import ru.bvt.jpanotesenginelite.rest.dto.NoteFullDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceSimple implements NoteService {

    private final Long defaultBookId;
    private final Long defaultAuthorId;
    private final NoteRepository repository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public NoteServiceSimple(@Value("${engine.defaults.book}") Long defaultBookId, @Value("${engine.defaults.author}") Long defaultAuthorId, NoteRepository repository, BookRepository bookRepository,
                             CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.defaultBookId = defaultBookId;
        this.defaultAuthorId = defaultAuthorId;
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }




    @Transactional(readOnly = true)
    public List<NoteFullDto> getAllNotes() {
      return repository.findAll().stream().map(NoteFullDto::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NoteFullDto getNote(long id) {
        return new NoteFullDto(repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + id)));
    }

    @Transactional(readOnly = false)
    public long addNote(NoteDto noteDto) {
        Note note = new Note(
                0,
                noteDto.getText(),
                getCurrentAuthor(),
                bookRepository.findById(noteDto.getBookId()).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + noteDto.getBookId())),
                prepareCategoryList(noteDto.getText()));
        note = repository.save(note);
        return note.getId();
    }

    @Transactional(readOnly = false)
    public long addNote(NoteBriefDto noteBriefDto) {
        Book book = bookRepository.findById(defaultBookId).orElseThrow(() -> new IllegalArgumentException("Not exist default book Id:1"));
        Note note = new Note(
                0,
                noteBriefDto.getText(),
                getCurrentAuthor(),
                book,
                prepareCategoryList(noteBriefDto.getText()));
        note = repository.save(note);
        return note.getId();
    }

    @Transactional(readOnly = false)
    public long setNote(NoteFullDto noteFullDto) {
        Book book = bookRepository.findById(noteFullDto.getBookId()).orElseThrow(() -> new IllegalArgumentException("Invalid bookId" + noteFullDto.getBookId()));
        Note note = new Note(
                noteFullDto.getId(),
                noteFullDto.getText(),
                getCurrentAuthor(),
                book,
                prepareCategoryList(noteFullDto.getText()));
        Note resultNote = repository.findById(note.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + note.getId()));
        resultNote.setText(note.getText());
        resultNote.setBook(note.getBook());
        resultNote.setAuthor(note.getAuthor());
        resultNote.setCategories(note.getCategories());
        resultNote = repository.save(resultNote);
        return resultNote.getId();
    }

    @Transactional(readOnly = false)
    public long deleteNote(long id) {
        repository.deleteById(id);
        return id;
    }

    private List<Category> prepareCategoryList(String noteTextWithCategoryHashtags) {
        List<Category> categoryList = new ArrayList<>();
        Category currentCategory = null;
        String currentString, substr = noteTextWithCategoryHashtags;
        while (substr.indexOf('#') != -1) {
            substr = substr.substring(substr.indexOf('#') + 1, substr.length());
            var nums = new int[]{
                    substr.indexOf(' ') > 0 ? substr.indexOf(' ') : substr.length(),
                    substr.indexOf('.') > 0 ? substr.indexOf('.') : substr.length(),
                    substr.indexOf(',') > 0 ? substr.indexOf(',') : substr.length(),
                    substr.indexOf(';') > 0 ? substr.indexOf(';') : substr.length(),
                    substr.length()};
            var min = Arrays.stream(nums).min();
            currentString = substr.substring(0, min.isPresent() ? min.getAsInt() : substr.length());
            if (currentString.length()>0) {
                currentCategory = categoryRepository.findByName(currentString);
                if (currentCategory == null) {
                    currentCategory = new Category(currentString);
                    categoryRepository.save(currentCategory);
                }
                categoryList.add(currentCategory);
            }
        }
        return categoryList;
    }

    private Author getCurrentAuthor() {
        Author currentAuthor = null;
        currentAuthor = authorRepository.findById(defaultAuthorId).orElseThrow(() -> new IllegalArgumentException("Invalid default author id :" + 1));
        return currentAuthor;
    }
}
