package ru.bvt.wf.wfmnotesengine.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bvt.wf.wfmnotesengine.domain.Category;
import ru.bvt.wf.wfmnotesengine.domain.Note;
import ru.bvt.wf.wfmnotesengine.repository.CategoryRepository;
import ru.bvt.wf.wfmnotesengine.repository.NoteRepository;
import ru.bvt.wf.wfmnotesengine.repository.ReactiveCategoryRepository;
import ru.bvt.wf.wfmnotesengine.repository.ReactiveNoteRepository;
import ru.bvt.wf.wfmnotesengine.service.NewNoteBufferKeeper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
public class NoteReactController {
    private final ReactiveNoteRepository repository;
    private final ReactiveCategoryRepository categoryRepository;
    private final NewNoteBufferKeeper newNoteBufferKeeper;

    @GetMapping("/flux/note")
    public Flux<Note> all() {
        return repository.findAll();
    }

    @GetMapping("/flux/note{id}")
    public Mono<Note> byId(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @GetMapping("/flux/note/bytext")
    public Flux<Note> byText(@RequestParam("text") String text) {
        return repository.findAllByText(text);
    }

    @GetMapping("/flux/note/byauthor")
    public Flux<Note> byAuthor(@RequestParam("author") String author) {
        return repository.findAllByAuthor(author);
    }

    @PostMapping("/flux/note")
    public Mono<Note> save(@RequestBody Mono<Note> dto) {
        return repository.save(dto);
    }


    @GetMapping(path = "/flux/note/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Note> feed() {
        return Flux.interval(Duration.ofSeconds(1))
                .onBackpressureDrop()
                .map(newNoteBufferKeeper::get)
                .flatMapIterable(x -> x);
    }
/*
    private List<Note> getNewNote(long interval) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        Note obj = new Note(dtf.format(LocalDateTime.now()));
        return Arrays.asList(obj);

    }
*/

    }


