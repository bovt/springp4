package ru.bvt.jdbcnotesenginelite.rest;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bvt.jdbcnotesenginelite.rest.dto.BookDto;
import ru.bvt.jdbcnotesenginelite.service.BookService;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping("/api/book")
    public List<BookDto> showBookList() {
        return service.getAllBooks();
    }

    @PostMapping("/api/book")
    public String createBook(@RequestBody BookDto newBookDto, BindingResult result) {
        if (result.hasErrors()) {
            return "createBook hadErrors";
        }
        long id = service.addBook(newBookDto);
        return new String("{ id: " + id + " }");
    }

    @GetMapping("/api/book/{id}")
    public BookDto showBook(@PathVariable("id") long id, Model model) {
        return service.getBook(id);
    }

    @PutMapping("/api/book/{id}")
    public String updateBook(@PathVariable("id") long id, @RequestBody BookDto bookDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "updateBook hasErrors";
        }
        bookDto.setId(id);
        return new String("{ id: " + service.setBook(bookDto) + " }");
    }

    @DeleteMapping("/api/book/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model) {
        service.deleteBook(id);
        return new String("{ id: " + id + " }");
    }
}
