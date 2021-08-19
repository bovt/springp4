package ru.bvt.notesengine.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bvt.notesengine.domain.Book;
import ru.bvt.notesengine.repository.BookRepositoryExtended;
import ru.bvt.notesengine.repository.BookRepositoryExtendedSimple;
import ru.bvt.notesengine.rest.dto.BookDto;

import java.util.List;

@AllArgsConstructor
@Service
public class BookServiceSimple implements BookService {

    private BookRepositoryExtended repository;

    @Transactional(readOnly = true)
    public List<BookDto> getAllBooks() {
        return repository.findAllAsDto();
    }

    @Transactional(readOnly = true)
    public BookDto getBook(long id) {
        return repository.findByIdAsDto(id);
    }

    @Transactional(readOnly = false)
    public long addBook(BookDto bookDto) {
        long id = bookDto.getId();
        if (repository.existsById(id)) {
            throw (new IllegalArgumentException("Invalid book Id:" + id));
        }
        bookDto.setId(0);
        return repository.create(bookDto);
    }

    @Transactional(readOnly = false)
    public long setBook(BookDto bookDto) {
        return repository.update(bookDto);
    }

    @Transactional(readOnly = false)
    public long deleteBook(long id) {
        Book book = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        repository.delete(book);
        return id;
    }

}
