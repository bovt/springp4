package ru.bvt.notesengine.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findAll();

    Optional<Book> findById(long id);

    Book save(Book book);

    void deleteById(long id);

    void delete(Book book);

    boolean existsById(Long id);

}
