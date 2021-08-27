package ru.bvt.notesengine.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.domain.Note;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    @EntityGraph(value = "authors-entity-graph")
    @Query("select n from Note n join fetch n.book")
    List<Note> findAll();

    boolean existsById(Integer integer);
    Optional<Note> findById(long id);
}
