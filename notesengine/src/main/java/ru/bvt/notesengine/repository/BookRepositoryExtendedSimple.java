package ru.bvt.notesengine.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bvt.notesengine.domain.Book;
import ru.bvt.notesengine.rest.dto.BookDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryExtendedSimple implements BookRepositoryExtended {

    private BookRepository repository;

    @Autowired
    BookRepositoryExtendedSimple(BookRepository bookCRUDRepository) {
        this.repository = bookCRUDRepository;
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Optional<Book> findById(long id) {
        return repository.findById(id);
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void delete(Book book) {
        repository.delete(book);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public long count() {
        return repository.count();
    }

    public List<BookDto> findAllAsDto() {
        return repository.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
    }

    public BookDto findByIdAsDto(long id) {
        Book book = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        return new BookDto(book);
    }

    public long create(BookDto bookDto) {
        Book book = new Book(bookDto.getName());
        book = repository.save(book);
        return book.getId();
    }

    public long update(BookDto bookDto) {
        Book book = repository.findById(bookDto.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + bookDto.getId()));
        book.setName(bookDto.getName());
        book = repository.save(book);
        return book.getId();
    }
}
