package ru.bvt.wf.wfmnotesengine.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bvt.wf.wfmnotesengine.domain.Category;
import ru.bvt.wf.wfmnotesengine.repository.ReactiveCategoryRepository;

import java.time.Duration;

@AllArgsConstructor
@RestController
public class CategoryReactController {
    private final ReactiveCategoryRepository repository;

    @GetMapping("/flux/category")
    public Flux<Category> all() {
        return repository.findAll();
    }

    @GetMapping("/flux/category{id}")
    public Mono<Category> byId(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @GetMapping("/flux/category/bytext")
    public Flux<Category> byName(@RequestParam("name") String name) {
        return repository.findAllByName(name);
    }

    @PostMapping("/flux/category")
    public Mono<Category> save(@RequestBody Mono<Category> dto) {
        return repository.save(dto);
    }
}


