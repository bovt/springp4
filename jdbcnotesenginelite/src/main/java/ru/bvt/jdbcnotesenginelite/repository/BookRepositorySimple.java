package ru.bvt.jdbcnotesenginelite.repository;

import org.springframework.stereotype.Repository;
import ru.bvt.jdbcnotesenginelite.domain.Author;
import ru.bvt.jdbcnotesenginelite.domain.Book;
import ru.bvt.jdbcnotesenginelite.domain.Category;
import ru.bvt.jdbcnotesenginelite.domain.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositorySimple implements BookRepository {
    public List<Book> findAll() {
        List<Book> list = new ArrayList<Book>();
        list.add(new Book("test"));
        return list;
    }

    ;

    public boolean existsById(long id) {
        return false;
    }

    ;

    public Book findById(long id) {
        return new Book("test");
    }

    ;

    public Book save(Book book) {
        return book;
    }

    public long deleteById(Long id) {
        return id;
    }

    ;

}
