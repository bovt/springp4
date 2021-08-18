package ru.bvt.notesengine.repository;

import ru.bvt.notesengine.domain.Book;
import ru.bvt.notesengine.rest.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryExtended {

    List<Book> findAll();

    Optional<Book> findById(long id);

    Book save(Book book);

    void deleteById(long id);

    void delete(Book book);

    boolean existsById(Long id);

    long count();

    List<BookDto> findAllAsDto();

    BookDto findByIdAsDto(long id);

    long create(BookDto bookDto);

    long update(BookDto bookDto);

}
