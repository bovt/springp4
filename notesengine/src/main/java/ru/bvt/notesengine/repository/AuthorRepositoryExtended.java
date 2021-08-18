package ru.bvt.notesengine.repository;

import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.rest.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorRepositoryExtended {

    List<Author> findAll();

    Optional<Author> findById(long id);

    Author save(Author author);

    void deleteById(long id);

    void delete(Author author);

    boolean existsById(Long id);

    Author findByName(String name);

    List<AuthorDto> findAllAsDto();

    AuthorDto findByIdAsDto(long id);

    long create(AuthorDto authorDto);

    long update(AuthorDto authorDto);
}
