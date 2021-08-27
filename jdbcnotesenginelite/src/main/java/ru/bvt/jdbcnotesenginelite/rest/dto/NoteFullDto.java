/*
 * Copyright 2016 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right name only.
 */
package ru.bvt.jdbcnotesenginelite.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bvt.jdbcnotesenginelite.domain.Author;
import ru.bvt.jdbcnotesenginelite.domain.Book;
import ru.bvt.jdbcnotesenginelite.domain.Category;
import ru.bvt.jdbcnotesenginelite.domain.Note;

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

        String bookName = "";
        Long bookId = new Long(0);
        Book book = note.getBook();
        if (book != null) {
            bookName = book.getName();
            bookId = book.getId();
        }
        String authorName = "";
        Long authorId = new Long(0);
        Author author = note.getAuthor();
        if (author != null) {
            authorName = author.getName();
            authorId = author.getId();
        }

        Long noteId = note.getId();
        if (noteId == null) {
            noteId = new Long(0);
        }

        this.id = noteId;
        this.text = note.getText();
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorId = authorId;
        this.authorName = authorName;

        this.categories = new HashMap<Long, String>();
        this.categoriesId = new ArrayList();
        this.categoriesName = new ArrayList();

        List<Category> noteCategories = note.getCategories();
        if (noteCategories != null) {
            for (Category category : noteCategories) {
                this.categoriesId.add(category.getId());
                this.categoriesName.add(category.getName());
                this.categories.put(category.getId(), category.getName());
            }
        }
    }

    public static NoteFullDto toDto(Note note) {
        List<Long> toDtoCategoriesId = new ArrayList();
        List<String> toDtoCategoriesName = new ArrayList();
        Map<Long, String> toDtoCategories = new HashMap<Long, String>();

        List<Category> noteCategories = note.getCategories();
        if (noteCategories != null) {
            for (Category category : noteCategories) {
                toDtoCategoriesId.add(category.getId());
                toDtoCategoriesName.add(category.getName());
                toDtoCategories.put(category.getId(), category.getName());
            }
        }
        Long noteId = note.getId();
        if (noteId == null) {
            noteId = new Long(0);
        }

        String bookName = "";
        Long bookId = new Long(0);
        Book book = note.getBook();
        if (book != null) {
            bookName = book.getName();
            bookId = book.getId();
        }
        String authorName = "";
        Long authorId = new Long(0);
        Author author = note.getAuthor();
        if (author != null) {
            authorName = author.getName();
            authorId = author.getId();
        }

        return new NoteFullDto(noteId, note.getText(),
                bookId, bookName, authorId, authorName,
                toDtoCategoriesId, toDtoCategoriesName, toDtoCategories);
    }
}
