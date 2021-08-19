package ru.bvt.notesengine.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.rest.dto.AuthorDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AuthorRepositoryExtendedSimple implements AuthorRepositoryExtended {

    private AuthorRepository repository;

    @Autowired
    AuthorRepositoryExtendedSimple(AuthorRepository authorCRUDRepository) {
        this.repository = authorCRUDRepository;
    }

    public List<Author> findAll() {
        return repository.findAll();
    }

    public Optional<Author> findById(long id) {
        return repository.findById(id);
    }

    public Author save(Author author) {
        return repository.save(author);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void delete(Author author) {
        repository.delete(author);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Author findByName(String name) {
        return repository.findByName(name);
    }

    public List<AuthorDto> findAllAsDto() {
        return repository.findAll().stream().map(AuthorDto::toDto).collect(Collectors.toList());
    }

    public AuthorDto findByIdAsDto(long id) {
        Author author = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + id));
        return new AuthorDto(author);
    }

    public long create(AuthorDto authorDto) {
        Author author = new Author(authorDto.getName(), authorDto.getPassword());
        author = repository.save(author);
        return author.getId();
    }

    public long update(AuthorDto authorDto) {
        Author author = repository.findById(authorDto.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + authorDto.getId()));
        author.setName(authorDto.getName());
        author.setPassword(authorDto.getPassword());
        author = repository.save(author);
        return author.getId();
    }

    ;
}
