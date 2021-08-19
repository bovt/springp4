/*
 * Copyright 2016 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right name only.
 */
package ru.bvt.notesengine.rest.dto;

import org.hibernate.annotations.BatchSize;
import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.domain.Book;
import ru.bvt.notesengine.domain.Category;
import ru.bvt.notesengine.domain.Note;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DTO that represents full view of Note
 * <p>
 * Описание используемых DTO для заметок:
 * NoteFullDto - для вывода полного списка параметров DTO
 * NoteBriefDto - для выстрого создания заметки из телеграм
 * только по тексту заметки (чтобы не тащить всю простыню
 * параметров в сторонние приложения)
 * NoteDto - для штатных Create/Update операций (для заметок
 * даётся возможность изменять только текст заметки
 * и номер книги в которой она содержится,
 * а автор и категория подставляются автоматически)
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteFullDto {

    private long id = -1;
    private String text;

    private long bookId;
    private String bookName;

    private long authorId;
    private String authorName;

    private List<Long> categoriesId;
    private List<String> categoriesName;
    Map<Long, String> categories = new HashMap<Long, String>();

    public NoteFullDto(Note note) {
        this.id = note.getId();
        this.text = note.getText();
        this.bookId = note.getBook().getId();
        this.bookName = note.getBook().getName();
        this.authorId = note.getAuthor().getId();
        this.authorName = note.getAuthor().getName();

        this.categories = new HashMap<Long, String>();
        this.categoriesId = new ArrayList();
        this.categoriesName = new ArrayList();
        for (Category category : note.getCategories()) {
            this.categoriesId.add(category.getId());
            this.categoriesName.add(category.getName());
            this.categories.put(category.getId(), category.getName());
        }
    }

    public static NoteFullDto toDto(Note note) {
        List<Long> toDtoCategoriesId = new ArrayList();
        List<String> toDtoCategoriesName = new ArrayList();
        Map<Long, String> toDtoCategories = new HashMap<Long, String>();

        for (Category category : note.getCategories()) {
            toDtoCategoriesId.add(category.getId());
            toDtoCategoriesName.add(category.getName());
            toDtoCategories.put(category.getId(), category.getName());
        }

        return new NoteFullDto(note.getId(), note.getText(),
                note.getBook().getId(), note.getBook().getName(), note.getAuthor().getId(), note.getAuthor().getName(),
                toDtoCategoriesId, toDtoCategoriesName, toDtoCategories);
    }
}
