package ru.bvt.wf.wfmnotesengine.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bvt.wf.wfmnotesengine.domain.Note;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findAll();
}

