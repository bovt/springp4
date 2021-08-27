package ru.bvt.jpanotesenginelite.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bvt.jpanotesenginelite.domain.Author;
import ru.bvt.jpanotesenginelite.domain.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class AuthorRepositorySimple implements AuthorRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();

    }

    ;

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    ;

    @Override
    public boolean existsById(long id) {
        return findById(id).isPresent();
    }

    ;

    @Override
    public Author save(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    ;

    @Override
    public long deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Author s " +
                "where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        return id;
    }

    ;

}
