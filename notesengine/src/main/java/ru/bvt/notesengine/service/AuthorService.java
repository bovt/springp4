package ru.bvt.notesengine.service;

import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.rest.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    public List<AuthorDto> getAllAuthors();

    public long addAuthor(AuthorDto newAuthorDto);

    public AuthorDto getAuthor(long id);

    public long setAuthor(AuthorDto authorDto);

    public long deleteAuthor(long id);

}
