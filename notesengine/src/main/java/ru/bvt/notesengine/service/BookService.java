package ru.bvt.notesengine.service;

import ru.bvt.notesengine.rest.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();

    long addBook(BookDto newBookDto);

    BookDto getBook(long id);

    long setBook(BookDto bookDto);

    long deleteBook(long id);

}
