package ru.bvt.jpanotesenginelite.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bvt.jpanotesenginelite.domain.Category;
import ru.bvt.jpanotesenginelite.domain.Note;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class CategoryRepositorySimple implements CategoryRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Category> findAll() {
        TypedQuery<Category> query = em.createQuery("select c from Category c", Category.class);
        return query.getResultList();
    }

    ;

    @Override
    public Optional<Category> findById(long id) {
        return Optional.ofNullable(em.find(Category.class, id));
    }

    ;

    @Override
    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    ;

    @Override
    public Category findByName(String name) {
        TypedQuery<Category> query = em.createQuery("select c " +
                        "from Category c " +
                        "where c.name = :name",
                Category.class);
        query.setParameter("name", name);
        return query.getResultList().get(1);
    }

    ;

    @Override
    public Category save(Category category) {
        if (category.getId() <= 0) {
            em.persist(category);
            return category;
        } else {
            return em.merge(category);
        }
    }

    ;

    @Override
    public long deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Category s " +
                "where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        return id;
    }

    ;

}
