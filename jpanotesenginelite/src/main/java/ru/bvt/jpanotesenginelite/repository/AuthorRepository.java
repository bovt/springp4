package ru.bvt.jpanotesenginelite.repository;

import ru.bvt.jpanotesenginelite.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> findAll();

    Optional<Author> findById(long id);

    boolean existsById(long id);

    Author save(Author author);

    long deleteById(long id);
}
