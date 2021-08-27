package ru.bvt.tgnotesagent.service;

import org.springframework.http.ResponseEntity;
import ru.bvt.tgnotesagent.rest.dto.AuthorDto;

import java.util.List;

public interface AuthorServiceWithCB {
    List<AuthorDto> getAllAuthors();
}
