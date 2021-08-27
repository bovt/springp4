package ru.bvt.jpanotesenginelite.repository;

import ru.bvt.jpanotesenginelite.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> findAll();

    Optional<Category> findById(long id);

    boolean existsById(Long id);

    Category findByName(String name);

    Category save(Category category);

    long deleteById(long id);
}
