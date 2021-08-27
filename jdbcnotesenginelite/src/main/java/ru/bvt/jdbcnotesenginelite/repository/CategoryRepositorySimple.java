package ru.bvt.jdbcnotesenginelite.repository;

import org.springframework.stereotype.Repository;
import ru.bvt.jdbcnotesenginelite.domain.Author;
import ru.bvt.jdbcnotesenginelite.domain.Category;
import ru.bvt.jdbcnotesenginelite.domain.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositorySimple implements CategoryRepository {
    public List<Category> findAll() {
        List<Category> list = new ArrayList<Category>();
        list.add(new Category("test"));
        return list;
    }

    ;

    public boolean existsById(long id) {
        return false;
    }

    ;

    public Category findById(long id) {
        return new Category("test");
    }

    ;

    public Category findByName(String name) {
        return new Category("byName");
    }

    ;

    public Category save(Category category) {
        return category;
    }

    public long deleteById(Long id) {
        return id;
    }

    ;

}
