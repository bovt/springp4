package ru.bvt.wf.wfmnotesengine.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bvt.wf.wfmnotesengine.domain.Category;
import ru.bvt.wf.wfmnotesengine.domain.Note;
import ru.bvt.wf.wfmnotesengine.repository.CategoryRepository;
import ru.bvt.wf.wfmnotesengine.repository.NoteRepository;

import java.util.*;

@AllArgsConstructor
@Service
public class NoteServiceSimple implements ru.bvt.wf.wfmnotesengine.service.NoteService {

    private final NoteRepository repository;
    private final CategoryRepository categoryRepository;
    private final NewNoteBufferKeeper newNoteBufferKeeper;

    @Transactional(readOnly = true)
    public List<Note> getAllNotes() {
      return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Note getNote(long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + id));
    }

    @Transactional(readOnly = false)
    public String addNote(Note noteDto) {
        Note newNote;

        // Возвращаем 0 если текст или объект пустой
        if ((noteDto == null) || (noteDto.getText() == null)) return "0";

        // Создаём note по тексту, добавляем автора если не пустой
        newNote = new Note(noteDto.getText());
        newNote.setAuthor(noteDto.getAuthor());
        if (newNote.getAuthor() == null) {newNote.setAuthor("");}

        // Создаём пустой хэш мэп по категориям заметки
        newNote = repository.save(newNote);
        cookCategoryMap(newNote);
        newNote = repository.save(newNote);
        newNoteBufferKeeper.put(newNote);
        return newNote.getId();
    }

    @Transactional(readOnly = false)
    public long deleteNote(long id) {
        Note note = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + id));
        repository.delete(note);
        return id;
    }

    private void cookCategoryMap(Note noteWithCategoryHashtagsAndValidNoteId) {
        Map<String, String> categoryMap = new HashMap<>();
        Category currentCategory = null;
        String currentString, substr = noteWithCategoryHashtagsAndValidNoteId.getText();

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
                if (currentCategory == null) { currentCategory = new Category(currentString); }
                currentCategory.addNewLastCreatedNotes(noteWithCategoryHashtagsAndValidNoteId);
                categoryRepository.save(currentCategory);

                categoryMap.put(currentCategory.getId(), currentCategory.getName());
            }
        }
        noteWithCategoryHashtagsAndValidNoteId.setCategories(categoryMap);
    }
}
