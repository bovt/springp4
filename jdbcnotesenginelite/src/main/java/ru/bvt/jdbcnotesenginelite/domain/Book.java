package ru.bvt.jdbcnotesenginelite.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private long id;
    private String name;

    public Book(String name) {
        this.name = name;
    }
}
