package ru.bvt.notesengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bvt.notesengine.domain.Author;
import ru.bvt.notesengine.domain.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAll();
    Optional<Book> findById(long id);
    boolean existsById(Long id);

}
