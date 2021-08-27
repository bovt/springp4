package ru.bvt.jdbcnotesenginelite.shell;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.bvt.jdbcnotesenginelite.rest.dto.*;
import ru.bvt.jdbcnotesenginelite.service.AuthorService;
import ru.bvt.jdbcnotesenginelite.service.BookService;
import ru.bvt.jdbcnotesenginelite.service.NoteService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ShellComponent
@AllArgsConstructor
public class NoteShell {
    NoteService noteService;
    BookService bookService;
    AuthorService authorService;

    @ShellMethod(value = "Create note", key = {"c", "create"})
    public String create(
            @ShellOption(defaultValue = "AnyText") String text) {
        long id = noteService.addNote(new NoteBriefDto(text));
        if (id == 0) {
            return String.format("Ошибка создания заметки");
        }
        return String.format("Создана заметка с номером: %s", id);
    }

    @ShellMethod(value = "List notes", key = {"l", "list"})
    public List<String> list() {
        List<NoteFullDto> noteList = noteService.getAllNotes();
        List<String> result = new ArrayList<>();
        if (noteList != null) {
            for (NoteFullDto noteDto : noteList) {
                result.add("NoteId = " + noteDto.getId() + "   Text = " + noteDto.getText());
            }
        }
        return result;
    }

    @ShellMethod(value = "Read note", key = {"r", "read"})
    public String read(
            @ShellOption(defaultValue = "4") String idInString) {
        Long id = Long.parseLong(idInString);
        if (id == 0) {
            return String.format("Ошибка чтения заметки");
        }
        NoteFullDto noteDto = noteService.getNote(id);
        if (noteDto == null) {
            return String.format("Ошибка чтения заметки");
        }
        return new String("NoteId = " + noteDto.getId() + "   Text = " + noteDto.getText());
    }

    @ShellMethod(value = "Update note", key = {"u", "update"})
    public String update(
            @ShellOption(defaultValue = "4") String idInString,
            @ShellOption(defaultValue = "new text") String newText,
            @ShellOption(defaultValue = "1") String newAuthorId,
            @ShellOption(defaultValue = "1") String newBookId) {
        Long id = Long.parseLong(idInString);
        Long bookId = Long.parseLong(idInString);
        Long authorId = Long.parseLong(idInString);
        if (id == 0 || bookId == 0 || authorId == 0) {
            return String.format("Ошибка записи заметки");
        }

        BookDto bookDto = bookService.getBook(bookId);
        AuthorDto authorDto = authorService.getAuthor(authorId);
        if (bookDto == null || authorDto == null) {
            return String.format("Ошибка записи заметки");
        }

        List<Long> toDtoCategoriesId = new ArrayList();
        List<String> toDtoCategoriesName = new ArrayList();
        Map<Long, String> toDtoCategories = new HashMap<Long, String>();

        NoteFullDto noteDto = new NoteFullDto(id, newText,
                bookId, bookDto.getName(), authorId, authorDto.getName(),
                toDtoCategoriesId, toDtoCategoriesName, toDtoCategories);
        if (noteDto == null) {
            return String.format("Ошибка записи заметки");
        }

        id = noteService.setNote(noteDto);
        if (id == 0) {
            return String.format("Ошибка записи заметки");
        }
        return new String("Запись заметки c id = " + id + " произведена.");
    }


    @ShellMethod(value = "Delete note", key = {"d", "delete"})
    public String delete(
            @ShellOption(defaultValue = "4") String idInString) {
        Long id = Long.parseLong(idInString);
        if (id == 0) {
            return String.format("Ошибка удаления заметки");
        }
        id = noteService.deleteNote(id);
        if (id == 0) {
            return String.format("Ошибка удаления заметки");
        }
        return new String("Удаление заметки c id = " + id + " произведено.");
    }
}
