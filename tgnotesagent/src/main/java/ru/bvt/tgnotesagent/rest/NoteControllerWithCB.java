package ru.bvt.tgnotesagent.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bvt.tgnotesagent.rest.dto.NoteFullDto;
import ru.bvt.tgnotesagent.service.NoteServiceWithCB;

import java.util.List;

@RestController
@AllArgsConstructor
public class NoteControllerWithCB {

    private final NoteServiceWithCB noteService;

    @GetMapping("/api/note")
    public List<NoteFullDto> showNoteList() {
        return noteService.getAllNotes();
    }

}
