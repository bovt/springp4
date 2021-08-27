package ru.bvt.wf.wfmnotesengine.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bvt.wf.wfmnotesengine.domain.Note;
import ru.bvt.wf.wfmnotesengine.repository.NoteRepository;
import ru.bvt.wf.wfmnotesengine.service.NoteService;

import java.time.Duration;
import java.util.List;

@AllArgsConstructor
@RestController
public class NoteController {
    private final NoteService service;

    @GetMapping("/api/note")
    public List<Note> showNoteList() {
        return service.getAllNotes();
    }

    @PostMapping("/api/note")
    public void createNote(@RequestBody Note noteDto) {
//    public String createNote(@RequestBody Note noteDto, BindingResult result) {
//        if (result.hasErrors()) {
//            return "createNote hadErrors";
//        }
        String id = service.addNote(noteDto);
//        return new String("{ id: " + id + " }");
    }

    @GetMapping("/api/note/{id}")
    public Note showNote(@PathVariable("id") long id, Model model) {
        return service.getNote(id);
    }

    @DeleteMapping("/api/note/{id}")
    public String deleteNote(@PathVariable("id") long id, Model model) {
        service.deleteNote(id);
        return new String("{ id: " + id + " }");
    }

}


