package ru.bvt.jpanotesenginelite.repository;

import ru.bvt.jpanotesenginelite.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Optional<Book> findById(long id);

    boolean existsById(Long id);

    Book save(Book book);

    long deleteById(long id);

}
