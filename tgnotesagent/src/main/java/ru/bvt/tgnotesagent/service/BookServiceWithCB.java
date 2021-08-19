package ru.bvt.tgnotesagent.service;

import ru.bvt.tgnotesagent.rest.dto.BookDto;

import java.util.List;

public interface BookServiceWithCB {
    public List<BookDto> getAllBooks();
}
