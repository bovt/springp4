package ru.bvt.notesengine.service;

import ch.qos.logback.core.hook.DelayingShutdownHook;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.domain.Note;
import ru.bvt.notesengine.repository.AuthorRepository;
import ru.bvt.notesengine.repository.AuthorRepositoryExtended;
import ru.bvt.notesengine.repository.AuthorRepositoryExtendedSimple;
import ru.bvt.notesengine.repository.NoteRepository;
import ru.bvt.notesengine.rest.dto.AuthorDto;
import ru.bvt.notesengine.rest.dto.NoteFullDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AuthorServiceSimple implements AuthorService {

    private AuthorRepositoryExtended repository;

    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthors() {
        return repository.findAllAsDto();
    }

    @Transactional(readOnly = true)
    public AuthorDto getAuthor(long id) {
        return repository.findByIdAsDto(id);
    }

    @Transactional(readOnly = false)
    public long addAuthor(AuthorDto authorDto) {
        long id = authorDto.getId();
        if (repository.existsById(id)) {
            throw (new IllegalArgumentException("Invalid author Id:" + id));
        }
        authorDto.setId(0);
        return repository.create(authorDto);
    }

    @Transactional(readOnly = false)
    public long setAuthor(AuthorDto authorDto) {
        return repository.update(authorDto);
    }

    @Transactional(readOnly = false)
    public long deleteAuthor(long id) {
        Author author = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        repository.delete(author);
        return id;
    }

}
