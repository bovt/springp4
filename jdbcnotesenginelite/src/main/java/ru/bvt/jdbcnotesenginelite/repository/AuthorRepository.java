package ru.bvt.jdbcnotesenginelite.repository;

import ru.bvt.jdbcnotesenginelite.domain.Author;

import java.util.List;

public interface AuthorRepository {

    List<Author> findAll();

    Author findById(long id);

    boolean existsById(long id);

    Author save(Author author);

    long deleteById(Long id);
}
