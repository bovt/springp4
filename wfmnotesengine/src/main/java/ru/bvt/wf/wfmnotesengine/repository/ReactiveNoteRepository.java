package ru.bvt.wf.wfmnotesengine.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bvt.wf.wfmnotesengine.domain.Note;

public interface ReactiveNoteRepository extends ReactiveMongoRepository<Note, String> {

    Flux<Note> findAll();

    Mono<Note> findById(String id);

    Mono<Note> save(Mono<Note> note);

    Flux<Note> findAllByText(String text);

    Flux<Note> findAllByAuthor(String author);
}

