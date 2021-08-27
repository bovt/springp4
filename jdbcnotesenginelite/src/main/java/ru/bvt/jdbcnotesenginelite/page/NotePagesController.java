package ru.bvt.jdbcnotesenginelite.page;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.bvt.jdbcnotesenginelite.domain.Note;
import ru.bvt.jdbcnotesenginelite.repository.NoteRepository;
import ru.bvt.jdbcnotesenginelite.rest.dto.NoteDto;
import ru.bvt.jdbcnotesenginelite.rest.dto.NoteFullDto;

@AllArgsConstructor
@Controller
public class NotePagesController {

    NoteRepository repository;

    @GetMapping("/aindex")
    public String alistPage(Model model) {
        model.addAttribute("keywords", "list authors");
        return "aindex";
    }

    @GetMapping(value = {"/nindex", "/index"})
    public String nlistPage(Model model) {
        model.addAttribute("keywords", "list notes");
        return "nindex";
    }

    @GetMapping(value = {"/nedit/{noteId}"})
    public String neditPage(@PathVariable("noteId") long noteId, Model model) {

        Note note = null;
        note = repository.findById(noteId);
        if (note == null) {
            model.addAttribute("errorMessage", "Note not found");
            return "error";
        }
        ;
        model.addAttribute("keywords", "edit note");
        model.addAttribute("add", false);
        model.addAttribute("idInModel", noteId);
        model.addAttribute("note", new NoteDto(note.getText(), note.getBook().getId()));
        return "nedit";
    }

    @GetMapping(value = {"/nadd"})
    public String naddPage(Model model) {
        model.addAttribute("keywords", "add note");
        model.addAttribute("add", true);
        NoteFullDto noteFullDto = new NoteFullDto();
        noteFullDto.setId(1);
        noteFullDto.setText("create!");
        noteFullDto.setBookId(1);
        model.addAttribute("note", noteFullDto);
        return "nedit";
    }


    @GetMapping("/bindex")
    public String blistPage(Model model) {
        model.addAttribute("keywords", "list books");
        return "bindex";
    }

    @GetMapping("/cindex")
    public String clistPage(Model model) {
        model.addAttribute("keywords", "list categories");
        return "cindex";
    }
}
