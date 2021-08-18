package ru.bvt.notesengine.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.domain.Book;
import ru.bvt.notesengine.domain.Category;
import ru.bvt.notesengine.domain.Note;
import ru.bvt.notesengine.repository.*;
import ru.bvt.notesengine.rest.dto.NoteBriefDto;
import ru.bvt.notesengine.rest.dto.NoteDto;
import ru.bvt.notesengine.rest.dto.NoteFullDto;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class NoteServiceSimple implements NoteService {

    private NoteRepositoryExtended repository;
    private BookRepositoryExtended bookRepository;
    private CategoryRepositoryExtended categoryRepository;
    private AuthorRepositoryExtended authorRepository;

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<NoteFullDto> getAllNotes() {
        EntityGraph<?> entityGraph = em.getEntityGraph("authors-entity-graph");
        TypedQuery<Note> query = em.createQuery("select n from Note n join fetch n.book", Note.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList().stream().map(NoteFullDto::toDto).collect(Collectors.toList());
//        return repository.findAllAsDto();
    }

    @Transactional(readOnly = true)
    public NoteFullDto getNote(long id) {
        return repository.findByIdAsDto(id);
    }

    @Transactional(readOnly = false)
    public long addNote(NoteDto noteDto) {
        return repository.create(new Note(
                0,
                noteDto.getText(),
                getCurrentAuthor(),
                bookRepository.findById(noteDto.getBookId()).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + noteDto.getBookId())),
                prepareCategoryList(noteDto.getText()))); // TODO: список категорий
    }

    @Transactional(readOnly = false)
    public long addNote(NoteBriefDto noteBriefDto) {
        Book book = bookRepository.findById(1).orElseThrow(() -> new IllegalArgumentException("Not exist default book Id:1"));
        return repository.create(new Note(
                0,
                noteBriefDto.getText(),
                getCurrentAuthor(),
                book,
                prepareCategoryList(noteBriefDto.getText())));

    }

    @Transactional(readOnly = false)
    public long setNote(NoteFullDto noteFullDto) {
        Book book = bookRepository.findById(noteFullDto.getBookId()).orElseThrow(() -> new IllegalArgumentException("Invalid bookId" + noteFullDto.getBookId()));
        return repository.update(new Note(
                noteFullDto.getId(),
                noteFullDto.getText(),
                getCurrentAuthor(),
                book,
                prepareCategoryList(noteFullDto.getText())));
    }

    @Transactional(readOnly = false)
    public long deleteNote(long id) {
        Note note = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + id));
        repository.delete(note);
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
            currentCategory = categoryRepository.findByName(currentString);
            if (currentCategory == null) {
                currentCategory = new Category(currentString);
                categoryRepository.save(currentCategory);
            }
            categoryList.add(currentCategory);
        }
        return categoryList;
    }

    private Author getCurrentAuthor() {
        Author currentAuthor = null;

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        currentAuthor = authorRepository.findByName(userDetails.getUsername());
        if (currentAuthor == null) {
            throw (new IllegalArgumentException("Invalid authenticated user not in authors"));
        }
        return currentAuthor;
    }
}
