package ru.bvt.jpanotesenginelite.service;

import ru.bvt.jpanotesenginelite.rest.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();

    long addBook(BookDto newBookDto);

    BookDto getBook(long id);

    long setBook(BookDto bookDto);

    long deleteBook(long id);

}
