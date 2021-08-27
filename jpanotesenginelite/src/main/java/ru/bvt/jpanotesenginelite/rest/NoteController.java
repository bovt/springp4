package ru.bvt.jpanotesenginelite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bvt.jpanotesenginelite.rest.dto.NoteBriefDto;
import ru.bvt.jpanotesenginelite.rest.dto.NoteDto;
import ru.bvt.jpanotesenginelite.rest.dto.NoteFullDto;
import ru.bvt.jpanotesenginelite.service.NoteService;

import java.util.List;

@RestController
public class NoteController {

    private final NoteService service;

    @Autowired
    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/api/note")
    public List<NoteFullDto> showNoteList() {
        return service.getAllNotes();
    }

    @PostMapping("/api/note")
    public String createNote(@RequestBody NoteDto newNoteDto, BindingResult result) {
        if (newNoteDto == null || result.hasErrors() || newNoteDto.getText()==null || newNoteDto.getBookId()==0) {
            return "Incorrect input data";
        }
        long id = service.addNote(newNoteDto);
        return new String("{ id: " + id + " }");
    }

    @PostMapping("/api/note/brief")
    public String createNoteFromBriefDTO(@RequestBody NoteBriefDto newBriefDto, BindingResult result) {
        if (newBriefDto == null || result.hasErrors() || newBriefDto.getText() == null) {
            return "Incorrect input data";
        }
        long id = service.addNote(newBriefDto);
        return new String("{ id: " + id + " }");
    }


    @GetMapping("/api/note/{id}")
    public NoteFullDto showNote(@PathVariable("id") long id, Model model) {
        return service.getNote(id);
    }

    @PutMapping("/api/note/{id}")
    public String updateNote(@PathVariable("id") long id, @RequestBody NoteDto noteDto, BindingResult result, Model model) {
        if (noteDto == null || result.hasErrors() || noteDto.getText()==null || noteDto.getBookId()==0) {
            return "Incorrect input data";
        }
        NoteFullDto noteFullDto = new NoteFullDto();
        noteFullDto.setId(id);
        noteFullDto.setBookId(noteDto.getBookId());
        noteFullDto.setText(noteDto.getText());
        return new String("{ id: " + service.setNote(noteFullDto) + " }");
    }

    @DeleteMapping("/api/note/{id}")
    public String deleteNote(@PathVariable("id") long id, Model model) {
        service.deleteNote(id);
        return new String("{ id: " + id + " }");
    }
}
