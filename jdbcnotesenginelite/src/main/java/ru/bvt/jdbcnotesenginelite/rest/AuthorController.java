package ru.bvt.jdbcnotesenginelite.rest;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bvt.jdbcnotesenginelite.rest.dto.AuthorDto;
import ru.bvt.jdbcnotesenginelite.service.AuthorService;

import java.util.List;

@RestController
@AllArgsConstructor
public class AuthorController {

    private final AuthorService service;

    @GetMapping("/api/author")
    public List<AuthorDto> showAuthorList() {
        return service.getAllAuthors();
    }

    @PostMapping("/api/author")
    public String createAuthor(@RequestBody AuthorDto newAuthorDto, BindingResult result) {
        if (result.hasErrors()) {
            return "createAuthor hadErrors";
        }
        long id = service.addAuthor(newAuthorDto);
        return new String("{ id: " + id + " }");
    }

    @GetMapping("/api/author/{id}")
    public AuthorDto showAuthor(@PathVariable("id") long id, Model model) {
        return service.getAuthor(id);
    }

    @PutMapping("/api/author/{id}")
    public String updateAuthor(@PathVariable("id") long id, @RequestBody AuthorDto authorDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "updateAuthor hasErrors";
        }
        authorDto.setId(id);
        return new String("{ id: " + service.setAuthor(authorDto) + " }");
    }

    @DeleteMapping("/api/author/{id}")
    public String deleteAuthor(@PathVariable("id") long id, Model model) {
        service.deleteAuthor(id);
        return new String("{ id: " + id + " }");
    }

}
