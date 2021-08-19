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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.domain.Note;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * DTO that represents full view of Author
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private long id;
    private String name;
    private String password;

    public AuthorDto(Author author) {
        id = author.getId();
        name = author.getName();
        password = author.getPassword();
    }

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getName(), author.getPassword());
    }
}
