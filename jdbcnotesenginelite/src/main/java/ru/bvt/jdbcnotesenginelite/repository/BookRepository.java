package ru.bvt.jdbcnotesenginelite.repository;

import ru.bvt.jdbcnotesenginelite.domain.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();

    Book findById(long id);

    boolean existsById(long id);

    Book save(Book book);

    long deleteById(Long id);

}
