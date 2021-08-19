package ru.bvt.tgnotesagent.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bvt.tgnotesagent.rest.dto.AuthorDto;
import ru.bvt.tgnotesagent.rest.dto.BookDto;
import ru.bvt.tgnotesagent.service.AuthorServiceWithCB;
import ru.bvt.tgnotesagent.service.BookServiceWithCB;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookControllerWithCB {

    private final BookServiceWithCB bookService;

    @GetMapping("/api/book")
    public List<BookDto> showBookList() {
        return bookService.getAllBooks();
    }

}
