package ru.bvt.jdbcnotesenginelite.repository;

import ru.bvt.jdbcnotesenginelite.domain.Author;
import ru.bvt.jdbcnotesenginelite.domain.Book;
import ru.bvt.jdbcnotesenginelite.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> findAll();

    Category findById(long id);

    boolean existsById(long id);

    Category findByName(String name);

    Category save(Category category);

    long deleteById(Long id);

}
