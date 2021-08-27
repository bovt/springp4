/*
 * Copyright 2016 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right name only.
 */
package ru.bvt.jpanotesenginelite.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bvt.jpanotesenginelite.domain.Book;

/**
 * DTO that represents full view of Author
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private long id;
    private String name;

    public BookDto(Book book) {
        id = book.getId();
        name = book.getName();
    }

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getName());
    }
}
