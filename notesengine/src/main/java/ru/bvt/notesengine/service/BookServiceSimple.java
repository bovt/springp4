package ru.bvt.notesengine.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bvt.notesengine.domain.Book;
import ru.bvt.notesengine.repository.BookRepository;
import ru.bvt.notesengine.rest.dto.BookDto;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookServiceSimple implements BookService {

    private final BookRepository repository;

    @Transactional(readOnly = true)
    public List<BookDto> getAllBooks() {
        return repository.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookDto getBook(long id) {
        Book book = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        return new BookDto(book);
    }

    @Transactional(readOnly = false)
    public long addBook(BookDto bookDto) {
        long id = bookDto.getId();
        if (repository.existsById(id)) {
            throw (new IllegalArgumentException("Invalid book Id:" + id));
        }
        Book book = new Book(bookDto.getName());
        book = repository.save(book);
        return book.getId();
    }

    @Transactional(readOnly = false)
    public long setBook(BookDto bookDto) {
        Book book = repository.findById(bookDto.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + bookDto.getId()));
        book.setName(bookDto.getName());
        book = repository.save(book);
        return book.getId();
    }

    @Transactional(readOnly = false)
    public long deleteBook(long id) {
        Book book = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        repository.delete(book);
        return id;
    }

}
