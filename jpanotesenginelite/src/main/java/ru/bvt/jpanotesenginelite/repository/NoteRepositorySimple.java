package ru.bvt.jpanotesenginelite.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bvt.jpanotesenginelite.domain.Note;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class NoteRepositorySimple implements NoteRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Note> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("authors-entity-graph");
        TypedQuery<Note> query = em.createQuery("select n from Note n join fetch n.book", Note.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    ;

    @Override
    public Optional<Note> findById(long id) {
        return Optional.ofNullable(em.find(Note.class, id));
    }

    ;

    @Override
    public Note save(Note note) {
        if (note.getId() <= 0) {
            em.persist(note);
            return note;
        } else {
            return em.merge(note);
        }
    }

    ;

    @Override
    public long deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Note s " +
                "where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        return id;
    }

    ;

}
