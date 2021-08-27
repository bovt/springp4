package ru.bvt.jpanotesenginelite.service;

import ru.bvt.jpanotesenginelite.rest.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAllAuthors();

    long addAuthor(AuthorDto newAuthorDto);

    AuthorDto getAuthor(long id);

    long setAuthor(AuthorDto authorDto);

    long deleteAuthor(long id);

}
