package ru.bvt.jdbcnotesenginelite.repository;

import org.springframework.stereotype.Repository;
import ru.bvt.jdbcnotesenginelite.domain.Author;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositorySimple implements AuthorRepository {

    public List<Author> findAll() {
        List<Author> list = new ArrayList<Author>();
        list.add(new Author("test", "pass"));
        return list;
    }

    public boolean existsById(long id) {
        return false;
    }

    ;

    public Author findById(long id) {
        return new Author("test", "pass");
    }

    ;

    public Author save(Author author) {
        return author;
    }

    public long deleteById(Long id) {
        return id;
    }

    ;

};
