package ru.bvt.notesengine.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.repository.AuthorRepository;
import ru.bvt.notesengine.rest.dto.AuthorDto;

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

    @Transactional(readOnly = true)
    public AuthorDto getAuthor(String name) {
        Author author = repository.findByName(name);
        return new AuthorDto(author);
    }


    @Transactional(readOnly = false)
    public long addAuthor(AuthorDto authorDto) {
        long id = authorDto.getId();
        if (repository.existsById(id)) {
            throw (new IllegalArgumentException("Invalid author Id:" + id));
        }
        Author author = new Author(authorDto.getName(), authorDto.getPassword(), authorDto.getRole());
        author = repository.save(author);
        return author.getId();
    }

    @Transactional(readOnly = false)
    public long setAuthor(AuthorDto authorDto) {
        Author author = repository.findById(authorDto.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + authorDto.getId()));
        author.setName(authorDto.getName());
        author.setPassword(authorDto.getPassword());
        author.setRole(authorDto.getRole());
        author = repository.save(author);
        return author.getId();
    }

    @Transactional(readOnly = false)
    public long deleteAuthor(long id) {
        Author author = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        repository.delete(author);
        return id;
    }

}
