package ru.bvt.jpanotesenginelite.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bvt.jpanotesenginelite.domain.Author;
import ru.bvt.jpanotesenginelite.repository.AuthorRepository;
import ru.bvt.jpanotesenginelite.rest.dto.AuthorDto;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AuthorServiceSimple implements AuthorService {

    private final AuthorRepository repository;

    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthors() {
        return repository.findAll().stream().map(AuthorDto::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AuthorDto getAuthor(long id) {
        Author author = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + id));
        return new AuthorDto(author);
    }

    @Transactional(readOnly = false)
    public long addAuthor(AuthorDto authorDto) {
        long id = authorDto.getId();
        if (repository.existsById(id)) {
            throw (new IllegalArgumentException("Invalid author Id:" + id));
        }
        Author author = new Author(authorDto.getName(), authorDto.getPassword());
        author = repository.save(author);
        return author.getId();
    }

    @Transactional(readOnly = false)
    public long setAuthor(AuthorDto authorDto) {
        Author author = repository.findById(authorDto.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + authorDto.getId()));
        author.setName(authorDto.getName());
        author.setPassword(authorDto.getPassword());
        author = repository.save(author);
        return author.getId();
    }

    @Transactional(readOnly = false)
    public long deleteAuthor(long id) {
        repository.deleteById(id);
        return id;
    }

}
