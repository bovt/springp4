package ru.bvt.tgnotesagent.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.bvt.tgnotesagent.rest.dto.AuthorDto;
import ru.bvt.tgnotesagent.service.AuthorServiceWithCB;

import java.util.List;

@RestController
@AllArgsConstructor
public class AuthorControllerWithCB {

    private final AuthorServiceWithCB authorService;

    @GetMapping("/api/author")
    public List<AuthorDto> showAuthorList() {
        return authorService.getAllAuthors();
    }

}
