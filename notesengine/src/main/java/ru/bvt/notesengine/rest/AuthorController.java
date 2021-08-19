package ru.bvt.notesengine.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bvt.notesengine.rest.dto.AuthorDto;
import ru.bvt.notesengine.service.AuthorService;
import ru.bvt.notesengine.service.AuthorServiceSimple;
import ru.bvt.notesengine.service.NoteServiceSimple;

import java.util.List;
import java.util.Random;

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
    public String updateAuthor(@PathVariable("id") long id, AuthorDto authorDto, BindingResult result, Model model) {
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
