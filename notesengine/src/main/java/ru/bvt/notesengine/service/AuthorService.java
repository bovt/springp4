package ru.bvt.notesengine.service;

import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.rest.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAllAuthors();

    long addAuthor(AuthorDto newAuthorDto);

    AuthorDto getAuthor(long id);

    AuthorDto getAuthor(String name);

    long setAuthor(AuthorDto authorDto);

    long deleteAuthor(long id);

}
