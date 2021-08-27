package ru.bvt.jdbcnotesenginelite.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.bvt.jdbcnotesenginelite.domain.Author;
import ru.bvt.jdbcnotesenginelite.domain.Book;
import ru.bvt.jdbcnotesenginelite.domain.Category;
import ru.bvt.jdbcnotesenginelite.domain.Note;
import ru.bvt.jdbcnotesenginelite.service.BookService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class NoteRepositorySimple implements NoteRepository {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public NoteRepositorySimple(BookService bookService, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        // Это просто оставили, чтобы не переписывать код
        // В идеале всё должно быть на NamedParameterJdbcOperations
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    public List<Note> findAll() {
        /*List<Note> list = new ArrayList<Note>();
        list.add(new Note("Note one"));
        list.add(new Note("Note two"));
        list.add(new Note("Note three"));
        return list; */
        return jdbc.query("select * from note", new NoteMapper());
    }

    ;

    public boolean existsById(long id) {
        return false;
    }

    ;

    @Override
    public Note findById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        // TODO: Добавить обработку некорректного Id
        return namedParameterJdbcOperations.queryForObject(
                "select * from note where id = :id", params, new NoteMapper()
        );
//        return new Note("test");
    }

    ;

    public Note save(Note note) {
        // TODO: добавить подстановку верных связей и категорий
        // TODO: добавить автогенерации id + перезаписывать во входящем note
        jdbc.update("insert into note (id, text, author_id, book_id) values (?, ?, ?, ?)", note.getId(), note.getText(), note.getAuthor().getId(), note.getBook().getId());
        return note;
        //        if (note.getId()==0) {note.setId(5);}; return note;
    }

    public long deleteById(Long id) {
        // TODO: Добавить обработку некорректного Id
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from note where id = :id", params
        );
        return id;
        // return 0;
    }

    ;

    private static class NoteMapper implements RowMapper<Note> {

        @Override
        public Note mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("text");
            long authorId = resultSet.getLong("author_id");
            long bookId = resultSet.getLong("book_id");

            // TODO: сделать подтягивание book/author join-ом
            List<Category> categories = new ArrayList<Category>();
            return new Note(id, name, new Author(authorId, "name", "password"),
                    new Book(bookId, "temp1"), categories);
        }
    }

}
