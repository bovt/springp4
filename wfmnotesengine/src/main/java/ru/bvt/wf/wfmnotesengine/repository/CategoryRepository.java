package ru.bvt.wf.wfmnotesengine.repository;

import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;
import ru.bvt.wf.wfmnotesengine.domain.Category;
import ru.bvt.wf.wfmnotesengine.domain.Note;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAll();

    Category findByName(String name);
}

